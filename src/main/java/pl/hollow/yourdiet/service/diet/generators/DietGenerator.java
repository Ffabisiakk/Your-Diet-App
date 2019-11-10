package pl.hollow.yourdiet.service.diet.generators;

import pl.hollow.yourdiet.entity.Meal;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.service.diet.Macros;

import java.util.List;

public interface DietGenerator {

    List<Meal> generateDiet(Macros macros, Setting setting);
}
