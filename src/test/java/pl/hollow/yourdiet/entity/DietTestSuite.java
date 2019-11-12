package pl.hollow.yourdiet.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.hollow.yourdiet.repository.DietRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DietTestSuite {

    @Autowired
    private DietRepo dietRepo;

    @Test
    public void shouldSaveDiet() {
//        Given
        Diet diet = new Diet();

//        When
        dietRepo.save(diet);

//        Then
        Long id = diet.getId();
        assertNotNull(id);

//        CleanUp
        dietRepo.deleteById(id);
    }

    @Test
    public void shouldFindDietById(){
//        Given
        Diet diet = new Diet();
        dietRepo.save(diet);
        Long id = diet.getId();

//        When
        Optional<Diet> fetchedDiet = dietRepo.findById(id);

//        Then
        assertNotNull(fetchedDiet.get());
        assertEquals(id, fetchedDiet.get().getId());

//        CleanUp
        dietRepo.deleteById(id);
    }

    @Test
    public void shouldFindAllDiets() {
//        Given
        Diet diet1 = new Diet();
        Diet diet2 = new Diet();
        dietRepo.save(diet1);
        dietRepo.save(diet2);
        Long id1 = diet1.getId();
        Long id2 = diet2.getId();

//        When
        List<Diet> fetchedDiets = dietRepo.findAll();

//        Then
        assertEquals(2, fetchedDiets.size());

//        CleanUp
        dietRepo.deleteById(id1);
        dietRepo.deleteById(id2);
    }


}