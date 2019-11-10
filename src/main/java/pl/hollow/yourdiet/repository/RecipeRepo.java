package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.Recipe;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    @Override
    List<Recipe> findAll();
}
