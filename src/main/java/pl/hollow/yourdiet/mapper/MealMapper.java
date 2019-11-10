package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.MealDto;
import pl.hollow.yourdiet.entity.Meal;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper {

    public MealDto mapToMealDto(Meal meal) {
        return new MealDto();
    }

    public Meal mapToMeal(MealDto mealDto) {
        return new Meal();
    }

    public List<MealDto> mapToRecipeDtoList(List<Meal> meals) {
        return meals.stream()
                .map(this::mapToMealDto)
                .collect(Collectors.toList());
    }
}
