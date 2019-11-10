package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.IngredientDto;
import pl.hollow.yourdiet.dto.spoonacular.ingredient.SpoonacularIngredientDto;
import pl.hollow.yourdiet.entity.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

    public IngredientDto mapToIngredientDto(Ingredient ingredient) {
        return new IngredientDto();
    }

    public Ingredient mapToIngredient(IngredientDto ingredientDto) {
        return new Ingredient();
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

    public List<Ingredient> mapToIngredientList(List<SpoonacularIngredientDto> ingredientDtos) {
        return ingredientDtos.stream()
                .map(this::mapToIngredient)
                .collect(Collectors.toList());
    }
}
