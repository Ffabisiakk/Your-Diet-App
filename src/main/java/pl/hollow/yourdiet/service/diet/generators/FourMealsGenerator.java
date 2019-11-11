package pl.hollow.yourdiet.service.diet.generators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.hollow.yourdiet.entity.Meal;
import pl.hollow.yourdiet.entity.Recipe;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.service.SpoonacularService;
import pl.hollow.yourdiet.service.diet.Macros;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class FourMealsGenerator extends CommonDietGenerator implements DietGenerator {

    @Autowired
    private SpoonacularService spoonacularService;

    void generateDay(Macros macros, Setting setting, List<Meal> diet, int day) {
        LocalTime firstMealTime = setting.getTimeOfFirstMeal();
        Setting.DietType dietType = setting.getDietType();
        String excludedIngredients = setting.getExcludedIngredients();

        List<Recipe> recipePool = spoonacularService.getRecipes(null, dietType, excludedIngredients, 100);

        Meal breakfast = new Meal();
        breakfast.setDayOfWeek(DayOfWeek.of(day));
        breakfast.setTime(firstMealTime);
        setRecipe(macros, dietType, excludedIngredients, recipePool, breakfast, 0.2);
        diet.add(breakfast);

        Meal lunch = new Meal();
        lunch.setDayOfWeek(DayOfWeek.of(day));
        lunch.setTime(firstMealTime.plusHours(4));
        setRecipe(macros, dietType, excludedIngredients, recipePool, lunch, 0.35);
        diet.add(lunch);

        Meal lunch2 = new Meal();
        lunch2.setDayOfWeek(DayOfWeek.of(day));
        lunch2.setTime(firstMealTime.plusHours(8));
        setRecipe(macros, dietType, excludedIngredients, recipePool, lunch2, 0.35);
        diet.add(lunch2);

        Meal dinner = new Meal();
        dinner.setDayOfWeek(DayOfWeek.of(day));
        dinner.setTime(firstMealTime.plusHours(12));
        setRecipe(macros, dietType, excludedIngredients, recipePool, dinner, 0.1);
        diet.add(dinner);
    }
}