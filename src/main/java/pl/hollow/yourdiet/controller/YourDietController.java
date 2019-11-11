package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hollow.yourdiet.dto.DietDto;
import pl.hollow.yourdiet.dto.RecipeDto;
import pl.hollow.yourdiet.entity.Diet;
import pl.hollow.yourdiet.entity.Ingredient;
import pl.hollow.yourdiet.entity.Recipe;
import pl.hollow.yourdiet.entity.User;
import pl.hollow.yourdiet.mapper.DietMapper;
import pl.hollow.yourdiet.mapper.RecipeMapper;
import pl.hollow.yourdiet.service.DbService;
import pl.hollow.yourdiet.service.SpoonacularService;
import pl.hollow.yourdiet.service.diet.YourDietService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("yourdiet")
public class YourDietController {

    @Autowired
    private DbService dbService;

    @Autowired
    private YourDietService yourDietService;

    @Autowired
    private SpoonacularService spoonacularService;

    @Autowired
    private DietMapper dietMapper;

    @Autowired
    private RecipeMapper recipeMapper;

    @PutMapping("generateDiet")
    public DietDto generateDiet(Long userId) throws Exception {
        if (dbService.getSetting(userId).isPresent()) {
            Optional<User> user = dbService.getUser(userId);
            if (user.isPresent()) {
                Diet diet = yourDietService.generateDiet(user.get());
                user.get().getDiets().add(diet);
                return dietMapper.mapToDietDto(diet);
            } else {
                throw new Exception("User are not defined");
            }
        } else {
            throw new Exception("Settings are not defined");
        }
    }

    @PutMapping("fetchIngredients")
    public RecipeDto fetchIngredients(Long dietId) throws Exception {
        Optional<Recipe> recipeOpt = dbService.getRecipe(dietId);
        if (recipeOpt.isPresent()) {
            List<Ingredient> ingredients = spoonacularService.getIngredientsByDietId(dietId);
            Recipe recipe = recipeOpt.get();
            recipe.setIngredients(ingredients);
            dbService.saveRecipe(recipe);
            return recipeMapper.mapToRecipeDto(recipe);
        } else {
            throw new Exception("No such recipe");
        }
    }
}
