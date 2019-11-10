package pl.hollow.yourdiet.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.hollow.yourdiet.config.SpoonacularConfig;
import pl.hollow.yourdiet.dto.spoonacular.ingredient.RecipeIngredientsDto;
import pl.hollow.yourdiet.dto.spoonacular.recipe.SearchRecipesDto;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.service.diet.Macros;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Component
public class SpoonacularClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpoonacularConfig spoonacularConfig;

    public SearchRecipesDto searchComplexRecipes(String query, Setting.DietType dietType, String excludedIngredients, Macros minMacros, Macros maxMacros, Integer number) {
        HashMap<String, String> params = new HashMap<>();
        ofNullable(query).ifPresent(q -> params.put("query", q));
        ofNullable(dietType).ifPresent(dt -> params.put("diet", dt.name()));
        ofNullable(excludedIngredients).ifPresent(ei -> params.put("excludedIngredients", ei));
        ofNullable(number).ifPresent(n -> params.put("number", n.toString()));
        ofNullable(minMacros).ifPresent(minM -> {
            params.put("minCarbs", String.valueOf(minM.getCarbs()));
            params.put("minProtein", String.valueOf(minM.getWhey()));
            params.put("minFat", String.valueOf(minM.getFat()));
        });
        ofNullable(maxMacros).ifPresent(maxM -> {
            params.put("minCarbs", String.valueOf(maxM.getCarbs()));
            params.put("minProtein", String.valueOf(maxM.getWhey()));
            params.put("minFat", String.valueOf(maxM.getFat()));
        });

        try {
            SearchRecipesDto searchRecipesDto = restTemplate.getForObject(generateURI("recipes/search", params), SearchRecipesDto.class);
            return ofNullable(searchRecipesDto).orElse(new SearchRecipesDto());
        } catch (RestClientException e) {
            e.printStackTrace();
            return new SearchRecipesDto();
        }
    }

    public RecipeIngredientsDto getRecipeIngredients(long recipeId) {
        String url = String.format("https://api.spoonacular.com/recipes/%d/ingredientWidget.json", recipeId);
        try {
            return restTemplate.getForObject(url, RecipeIngredientsDto.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new RecipeIngredientsDto();
        }
    }

    private URI generateURI(String endpoint, HashMap<String, String> params) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(spoonacularConfig.getSpoonEndpoint() + endpoint);
        url.queryParam("apiKey", spoonacularConfig.getSpoonKey());
        for (Map.Entry<String, String> param : params.entrySet()) {
            url.queryParam(param.getKey(), param.getValue());
        }
        return url.build().encode().toUri();
    }

    private URI generateURI(String endpoint) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(spoonacularConfig.getSpoonEndpoint() + endpoint);
        url.queryParam("apiKey", spoonacularConfig.getSpoonKey());
        return url.build().encode().toUri();
    }
}
