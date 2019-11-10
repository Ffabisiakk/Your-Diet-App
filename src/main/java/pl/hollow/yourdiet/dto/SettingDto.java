package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.hollow.yourdiet.entity.Setting;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SettingDto {

    private Long id;
    private UserDto user;
    private Setting.ActivityLevel activityLevel;
    private Setting.DietType dietType;
    private int numberOfMeals;
    private String excludedIngredients;
    private int targetWeight;
    private int variationOfCalories;
    private LocalTime timeOfFirstMeal;
}
