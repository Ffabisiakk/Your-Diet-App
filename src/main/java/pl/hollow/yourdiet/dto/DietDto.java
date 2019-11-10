package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DietDto {
    private long id;
    private int kcal;
    private int whey;
    private int carbs;
    private int fat;
    private UserDto user;
    private List<MealDto> meals;
}
