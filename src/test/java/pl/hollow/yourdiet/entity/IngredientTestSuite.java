package pl.hollow.yourdiet.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.hollow.yourdiet.repository.IngredientRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientTestSuite {

    @Autowired
    private IngredientRepo ingredientRepo;

    @Test
    public void shouldSaveIngredient() {
//        Given
        Ingredient ingredient = new Ingredient();

//        When
        ingredientRepo.save(ingredient);

//        Then
        Long id = ingredient.getId();
        assertNotNull(id);

//        CleanUp
        ingredientRepo.deleteById(id);
    }

    @Test
    public void shouldFindIngredientById() {
//        Given
        Ingredient ingredient = new Ingredient();
        ingredientRepo.save(ingredient);
        Long id = ingredient.getId();

//        When
        Optional<Ingredient> fetchedIngredient = ingredientRepo.findById(id);

//        Then
        assertNotNull(fetchedIngredient.get());
        assertEquals(id, fetchedIngredient.get().getId());

//        CleanUp
        ingredientRepo.deleteById(id);
    }

    @Test
    public void shouldFindAllIngredients() {
//        Given
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        ingredientRepo.save(ingredient1);
        ingredientRepo.save(ingredient2);
        Long id1 = ingredient1.getId();
        Long id2 = ingredient2.getId();

//        When
        List<Ingredient> fetchedIngredients = ingredientRepo.findAll();

//        Then
        assertEquals(2, fetchedIngredients.size());

//        CleanUp
        ingredientRepo.deleteById(id1);
        ingredientRepo.deleteById(id2);
    }

}