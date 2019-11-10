package pl.hollow.yourdiet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hollow.yourdiet.client.SpoonacularClient;
import pl.hollow.yourdiet.dto.spoonacular.ingredient.RecipeIngredientsDto;
import pl.hollow.yourdiet.dto.spoonacular.recipe.SearchRecipesDto;
import pl.hollow.yourdiet.dto.spoonacular.recipe.SpoonacularRecipeDto;
import pl.hollow.yourdiet.entity.Ingredient;
import pl.hollow.yourdiet.entity.Recipe;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.mapper.IngredientMapper;
import pl.hollow.yourdiet.mapper.RecipeMapper;
import pl.hollow.yourdiet.service.diet.Macros;

import java.util.List;

@Service
public class SpoonacularService {

    @Autowired
    private SpoonacularClient spoonacularClient;

    @Autowired
    private DbService dbService;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private IngredientMapper ingredientMapper;

    public List<Recipe> getRecipes(String query, Setting.DietType dietType, String excludedIngredients, int number) {
        SearchRecipesDto searchRecipesDto = spoonacularClient
                .searchComplexRecipes(query, dietType, excludedIngredients, null, null, number);
        List<SpoonacularRecipeDto> spoonacularRecipeDtos = searchRecipesDto.getRecipes();
        return recipeMapper.mapToRecipeList(spoonacularRecipeDtos);
    }

    public Recipe getRecipeByNutritions(String query, Setting.DietType dietType, String excludedIngredients, Macros min, Macros max) {
        SearchRecipesDto searchRecipesDto = spoonacularClient
                .searchComplexRecipes(query, dietType, excludedIngredients, min, max, 1);
        SpoonacularRecipeDto spoonacularRecipeDto = searchRecipesDto.getRecipes().get(0);
        return recipeMapper.mapToRecipeFromSpoonacular(spoonacularRecipeDto);
    }

    public List<Ingredient> getIngredientsByDietId(Long dietId) {
        RecipeIngredientsDto recipeIngredientsDto = spoonacularClient.getRecipeIngredients(dietId);
        List<Ingredient> ingredients = ingredientMapper.mapToIngredientListSpoon(recipeIngredientsDto.getIngredients());
        Recipe recipe = dbService.getRecipe(dietId).get();
        ingredients.forEach(i -> i.setRecipe(recipe));
        return ingredients;
    }
}
