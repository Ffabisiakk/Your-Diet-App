package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.IngredientDto;
import pl.hollow.yourdiet.mapper.IngredientMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private DbService dbService;

    @Autowired
    private IngredientMapper ingredientMapper;

    @GetMapping()
    public List<IngredientDto> getIngredients() {
        return ingredientMapper.mapToIngredientDtoList(dbService.getIngredients());
    }

    @GetMapping("{id}")
    public IngredientDto getIngredient(@PathVariable Long id) {
        return ingredientMapper.mapToIngredientDto(dbService.getIngredient(id).orElseThrow());
    }

    @PostMapping()
    public void createIngredient(@RequestBody IngredientDto ingredientDto) {
        dbService.saveIngredient(ingredientMapper.mapToIngredient(ingredientDto));
    }

    @PutMapping()
    public IngredientDto updateIngredient(@RequestBody IngredientDto ingredientDto) {
        return ingredientMapper.mapToIngredientDto(dbService.saveIngredient(ingredientMapper.mapToIngredient(ingredientDto)));
    }

    @DeleteMapping("{id}")
    public void deleteIngredient(@PathVariable Long id) {
        dbService.deleteIngredient(id);
    }
}
