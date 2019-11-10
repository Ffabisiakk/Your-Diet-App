package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
}
