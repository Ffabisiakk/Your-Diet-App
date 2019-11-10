package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.Meal;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MealRepo extends CrudRepository<Meal, Long> {

    @Override
    List<Meal> findAll();
}
