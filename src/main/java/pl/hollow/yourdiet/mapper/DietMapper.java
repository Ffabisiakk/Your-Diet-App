package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.DietDto;
import pl.hollow.yourdiet.entity.Diet;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DietMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MealMapper mealMapper;

    public DietDto mapToDietDto(Diet diet) {
        return new DietDto(diet.getId(), userMapper.mapToUserDto(diet.getUser()), mealMapper.mapToMealDtoList(diet.getMeals()),
                diet.getKcal(), diet.getWhey(), diet.getCarbs(), diet.getFat());
    }

    public Diet mapToDiet(DietDto dietDto) {
        return new Diet(dietDto.getId(), userMapper.mapToUser(dietDto.getUser()), mealMapper.mapToMealList(dietDto.getMeals()),
                dietDto.getKcal(), dietDto.getWhey(), dietDto.getCarbs(), dietDto.getFat());
    }

    public List<DietDto> mapToDietDtoList(List<Diet> diets) {
        return diets.stream()
                .map(this::mapToDietDto)
                .collect(Collectors.toList());
    }

    public List<Diet> mapToDietList(List<DietDto> diets) {
        return diets.stream()
                .map(this::mapToDiet)
                .collect(Collectors.toList());
    }
}
