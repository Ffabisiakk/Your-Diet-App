package pl.hollow.yourdiet.service.diet.generators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.hollow.yourdiet.entity.Meal;
import pl.hollow.yourdiet.entity.Recipe;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.service.SpoonacularService;
import pl.hollow.yourdiet.service.diet.Macros;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CommonDietGenerator implements DietGenerator {

    @Autowired
    private SpoonacularService spoonacularService;

    abstract void generateDay(Macros macros, Setting setting, List<Meal> diet, int day);

    @Override
    public List<Meal> generateDiet(Macros macros, Setting setting) {

        List<Meal> diet = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            generateDay(macros, setting, diet, i);
        }
        return diet;
    }

    void setRecipe(Macros macros, Setting.DietType dietType, String excludedIngredients, List<Recipe> recipePool, Meal meal, double portion) {
        Optional<Recipe> recipe;
        recipe = recipePool.stream()
                .filter(r -> r.getCalories() > macros.getKcal() * (portion - 0.05) && r.getCalories() < macros.getKcal() * (portion + 0.05))
                .findAny();
        if (recipe.isPresent()) {
            meal.setRecipe(recipe.get());
        } else {
            Macros min = new Macros(macros, (portion - 0.1));
            Macros max = new Macros(macros, (portion + 0.1));
            meal.setRecipe(spoonacularService.getRecipeByNutritions(null, dietType, excludedIngredients, min, max));
        }
    }

}
