package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.RecipeDto;
import pl.hollow.yourdiet.dto.spoonacular.recipe.SpoonacularRecipeDto;
import pl.hollow.yourdiet.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeDto mapToRecipeDto(Recipe recipe) {
        return new RecipeDto();
    }

    public Recipe mapToRecipe(RecipeDto recipeDto) {
        return new Recipe();
    }

    public List<RecipeDto> mapToRecipeDtoList(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::mapToRecipeDto)
                .collect(Collectors.toList());
    }

    public Recipe mapToRecipeFromSpoonacular(SpoonacularRecipeDto spoonacularRecipeDto) {
        return new Recipe();
    }

    public List<Recipe> mapToRecipeList(List<SpoonacularRecipeDto> searchComplexRecipes) {
        return searchComplexRecipes.stream()
                .map(this::mapToRecipeFromSpoonacular)
                .collect(Collectors.toList());
    }
}
