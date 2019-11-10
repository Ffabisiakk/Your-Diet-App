package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.MealDto;
import pl.hollow.yourdiet.mapper.MealMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("meals")
public class MealController {

    @Autowired
    private DbService dbService;

    @Autowired
    private MealMapper mealMapper;

    @GetMapping()
    public List<MealDto> getRecipes() {
        return mealMapper.mapToRecipeDtoList(dbService.getMeals());
    }

    @GetMapping("{id}")
    public MealDto getMeal(@PathVariable Long id) {
        return mealMapper.mapToMealDto(dbService.getMeal(id).orElseThrow());
    }

    @PostMapping()
    public void createMeal(@RequestBody MealDto mealDto) {
         dbService.saveMeal(mealMapper.mapToMeal(mealDto));
    }

    @PutMapping()
    public MealDto updateMeal(@RequestBody MealDto mealDto) {
        return mealMapper.mapToMealDto(dbService.saveMeal(mealMapper.mapToMeal(mealDto)));
    }

    @DeleteMapping("(id}")
    public void deleteMeal(@PathVariable Long id) {
        dbService.deleteMeal(id);
    }
}
