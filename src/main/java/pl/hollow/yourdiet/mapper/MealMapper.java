package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.MealDto;
import pl.hollow.yourdiet.entity.Meal;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper {

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private DietMapper dietMapper;

    public MealDto mapToMealDto(Meal meal) {
        return new MealDto(meal.getId(), recipeMapper.mapToRecipeDto(meal.getRecipe()),
                dietMapper.mapToDietDto(meal.getDiet()), meal.getDayOfWeek(), meal.getTime());
    }

    public Meal mapToMeal(MealDto mealDto) {
        return new Meal(mealDto.getId(), recipeMapper.mapToRecipe(mealDto.getRecipe()),
                dietMapper.mapToDiet(mealDto.getDietDto()), mealDto.getDayOfWeek(), mealDto.getTime());
    }

    public List<MealDto> mapToMealDtoList(List<Meal> meals) {
        return meals.stream()
                .map(this::mapToMealDto)
                .collect(Collectors.toList());
    }

    public List<Meal> mapToMealList(List<MealDto> mealDtos) {
        return mealDtos.stream()
                .map(this::mapToMeal)
                .collect(Collectors.toList());
    }
}
