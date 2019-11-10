package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.RecipeDto;
import pl.hollow.yourdiet.dto.spoonacular.recipe.SpoonacularRecipeDto;
import pl.hollow.yourdiet.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    @Autowired
    private MealMapper mealMapper;

    @Autowired
    private IngredientMapper ingredientMapper;

    public RecipeDto mapToRecipeDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(), mealMapper.mapToMealDtoList(recipe.getMeals()),
                ingredientMapper.mapToIngredientDtoList(recipe.getIngredients()), recipe.getTitle(),
                recipe.getCalories(), recipe.getCarbs(), recipe.getFat(), recipe.getProtein());
    }

    public Recipe mapToRecipe(RecipeDto recipeDto) {
        return new Recipe(recipeDto.getId(), mealMapper.mapToMealList(recipeDto.getMeals()),
                ingredientMapper.mapToIngredientList(recipeDto.getIngredients()), recipeDto.getTitle(),
                recipeDto.getCalories(), recipeDto.getCarbs(), recipeDto.getFat(), recipeDto.getProtein());
    }

    public List<RecipeDto> mapToRecipeDtoList(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::mapToRecipeDto)
                .collect(Collectors.toList());
    }

    public Recipe mapToRecipeFromSpoonacular(SpoonacularRecipeDto recipeDto) {
        return new Recipe(recipeDto.getId(), null, null, recipeDto.getTitle(),
                Integer.parseInt(recipeDto.getCalories().replace("g","")),
                Integer.parseInt(recipeDto.getCarbs().replace("g","")),
                Integer.parseInt(recipeDto.getFat().replace("g","")),
                Integer.parseInt(recipeDto.getProtein().replace("g","")));
    }

    public List<Recipe> mapToRecipeList(List<SpoonacularRecipeDto> searchComplexRecipes) {
        return searchComplexRecipes.stream()
                .map(this::mapToRecipeFromSpoonacular)
                .collect(Collectors.toList());
    }
}
