package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.IngredientDto;
import pl.hollow.yourdiet.dto.spoonacular.ingredient.SpoonacularIngredientDto;
import pl.hollow.yourdiet.entity.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    @Autowired
    private RecipeMapper recipeMapper;

    public IngredientDto mapToIngredientDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), recipeMapper.mapToRecipeDto(ingredient.getRecipe()),
                ingredient.getName(), ingredient.getAmount());
    }

    public Ingredient mapToIngredient(IngredientDto ingredientDto) {
        return new Ingredient(ingredientDto.getId(), recipeMapper.mapToRecipe(ingredientDto.getRecipeDto()),
                ingredientDto.getName(), ingredientDto.getAmount());
    }

    public List<IngredientDto> mapToIngredientDtoList(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::mapToIngredientDto)
                .collect(Collectors.toList());
    }

    public Ingredient mapToIngredient(SpoonacularIngredientDto ingredientDto) {
        String amount = ingredientDto.getAmount().getMetric().getValue() + ingredientDto.getAmount().getMetric().getUnit();
        return new Ingredient(null, null, ingredientDto.getName(), amount);
    }

    public List<Ingredient> mapToIngredientListSpoon(List<SpoonacularIngredientDto> ingredientDtos) {
        return ingredientDtos.stream()
                .map(this::mapToIngredient)
                .collect(Collectors.toList());
    }

    public List<Ingredient> mapToIngredientList(List<IngredientDto> ingredients) {
        return ingredients.stream()
                .map(this::mapToIngredient)
                .collect(Collectors.toList());
    }
}
