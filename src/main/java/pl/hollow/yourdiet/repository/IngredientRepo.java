package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.Ingredient;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    @Override
    List<Ingredient> findAll();
}
