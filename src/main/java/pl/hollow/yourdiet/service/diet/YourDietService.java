package pl.hollow.yourdiet.service.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hollow.yourdiet.client.SpoonacularClient;
import pl.hollow.yourdiet.entity.*;
import pl.hollow.yourdiet.service.DbService;
import pl.hollow.yourdiet.service.diet.generators.DietGenerator;
import pl.hollow.yourdiet.service.diet.generators.FiveMealsGenerator;
import pl.hollow.yourdiet.service.diet.generators.FourMealsGenerator;
import pl.hollow.yourdiet.service.diet.generators.ThreeMealsGenerator;

@Service
public class YourDietService {

    @Autowired
    private SpoonacularClient spoonacularClient;

    @Autowired
    private DbService dbService;

    public Diet generateDiet(User user) {
        Setting setting = dbService.getSetting(user.getId()).get();
        DietGenerator dietGenerator;
        switch (setting.getNumberOfMeals()) {
            case 3:
                dietGenerator = new ThreeMealsGenerator();
                break;
            case 4:
                dietGenerator = new FourMealsGenerator();
                break;
            case 5:
                dietGenerator = new FiveMealsGenerator();
                break;
            default:
                throw new IllegalArgumentException();
        }
        Diet diet = new Diet();
        Macros macros = new Macros(user, setting);
        diet.setMeals(dietGenerator.generateDiet(macros, setting));

        setSummarizedMacros(diet);

        return diet;
    }

    private void setSummarizedMacros(Diet diet) {
        int kcal = 0;
        int whey = 0;
        int carbs = 0;
        int fat = 0;

        for (Meal meal : diet.getMeals()) {
            Recipe recipe = meal.getRecipe();

            kcal += recipe.getCalories();
            whey += recipe.getProtein();
            carbs += recipe.getCarbs();
            fat += recipe.getFat();
        }

        diet.setKcal(kcal);
        diet.setWhey(whey);
        diet.setCarbs(carbs);
        diet.setFat(fat);
    }

}
