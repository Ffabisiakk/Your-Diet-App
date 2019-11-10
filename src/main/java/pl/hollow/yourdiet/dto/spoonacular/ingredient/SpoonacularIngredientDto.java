package pl.hollow.yourdiet.dto.spoonacular.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(value = "true")
public class SpoonacularIngredientDto {

    private Amount amount;
    private String name;
}
