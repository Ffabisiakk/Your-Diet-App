package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecipeDto {

    private long id;
    private List<MealDto> meals;
    private List<IngredientDto> ingredients;
    private String title;
    private int calories;
    private int carbs;
    private int fat;
    private int protein;
}
