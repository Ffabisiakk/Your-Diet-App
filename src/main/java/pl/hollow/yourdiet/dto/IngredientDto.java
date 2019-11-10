package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientDto {

    private Long id;

    private RecipeDto recipeDto;

    private String name;
    private String amount;
}
