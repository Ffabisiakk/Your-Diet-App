package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MealDto {

    private long id;
    private RecipeDto recipe;
    private DietDto dietDto;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
}
