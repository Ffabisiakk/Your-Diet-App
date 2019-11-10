package pl.hollow.yourdiet.dto.spoonacular.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(value = "true")
public class RecipeIngredientsDto {

    private List<SpoonacularIngredientDto> ingredients;
}
