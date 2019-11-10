package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.Diet;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DietRepo extends CrudRepository<Diet, Long> {

    @Override
    List<Diet> findAll();
}
