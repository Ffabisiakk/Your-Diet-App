package pl.hollow.yourdiet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hollow.yourdiet.entity.*;
import pl.hollow.yourdiet.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired private DietRepo dietRepo;
    @Autowired private IngredientRepo ingredientRepo;
    @Autowired private MealRepo mealRepo;
    @Autowired private SettingRepo settingRepo;
    @Autowired private UserRepo userRepo;
    @Autowired private RecipeRepo recipeRepo;

    public List<Diet> getDiets() {
        return dietRepo.findAll();
    }

    public Optional<Diet> getDiet(Long id) {
        return dietRepo.findById(id);
    }

    public Diet saveDiet(Diet diet) {
        return dietRepo.save(diet);
    }

    public void deleteDiet(Long id) {
        dietRepo.deleteById(id);
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepo.findAll();
    }

    public Optional<Ingredient> getIngredient(Long id) {
        return ingredientRepo.findById(id);
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepo.deleteById(id);
    }

    public List<Meal> getMeals() {
        return mealRepo.findAll();
    }

    public Optional<Meal> getMeal(Long id) {
        return mealRepo.findById(id);
    }

    public Meal saveMeal(Meal meal) {
        return mealRepo.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepo.deleteById(id);
    }

    public List<Recipe> getRecipes() {
        return recipeRepo.findAll();
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepo.findById(id);
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    public List<Setting> getSettings() {
        return settingRepo.findAll();
    }

    public Optional<Setting> getSetting(Long id) {
        return settingRepo.findById(id);
    }

    public Setting saveSetting(Setting setting) {
        return settingRepo.save(setting);
    }

    public void deleteSetting(Long id) {
        settingRepo.deleteById(id);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
