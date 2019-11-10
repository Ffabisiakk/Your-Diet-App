package pl.hollow.yourdiet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hollow.yourdiet.entity.Setting;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SettingRepo extends CrudRepository<Setting, Long> {

    @Override
    List<Setting> findAll();
}
