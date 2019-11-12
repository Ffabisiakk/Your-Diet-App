package pl.hollow.yourdiet.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.hollow.yourdiet.repository.MealRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealTestSuite {

    @Autowired
    private MealRepo mealRepo;

    @Test
    public void shouldSaveMeal() {
//        Given
        Meal meal = new Meal();

//        When
        mealRepo.save(meal);

//        Then
        Long id = meal.getId();
        assertNotNull(id);

//        CleanUp
        mealRepo.deleteById(id);
    }

    @Test
    public void shouldFindMealById() {
//        Given
        Meal meal = new Meal();
        mealRepo.save(meal);
        Long id = meal.getId();

//        When
        Optional<Meal> fetchedMeal = mealRepo.findById(id);

//        Then
        assertNotNull(fetchedMeal.get());
        assertEquals(id, fetchedMeal.get().getId());

//        CleanUp
        mealRepo.deleteById(id);
    }

    @Test
    public void shouldFindAllMeals() {
//        Given
        Meal meal1 = new Meal();
        Meal meal2 = new Meal();
        mealRepo.save(meal1);
        mealRepo.save(meal2);
        Long id1 = meal1.getId();
        Long id2 = meal2.getId();

//        When
        List<Meal> fetchedMeals = mealRepo.findAll();

//        Then
        assertEquals(2, fetchedMeals.size());

//        CleanUp
        mealRepo.deleteById(id1);
        mealRepo.deleteById(id2);
    }
}