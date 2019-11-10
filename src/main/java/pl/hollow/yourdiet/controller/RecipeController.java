package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.RecipeDto;
import pl.hollow.yourdiet.mapper.RecipeMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private DbService dbService;

    @Autowired
    private RecipeMapper recipeMapper;

    @GetMapping()
    public List<RecipeDto> getRecipes() {
        return recipeMapper.mapToRecipeDtoList(dbService.getRecipes());
    }

    @GetMapping("{id}")
    public RecipeDto getRecipe(@PathVariable Long id) {
        return recipeMapper.mapToRecipeDto(dbService.getRecipe(id).orElseThrow());
    }

    @PostMapping()
    public void createRecipe(@RequestBody RecipeDto recipeDto) {
        dbService.saveRecipe(recipeMapper.mapToRecipe(recipeDto));
    }

    @PutMapping()
    public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeMapper.mapToRecipeDto(dbService.saveRecipe(recipeMapper.mapToRecipe(recipeDto)));
    }

    @DeleteMapping("(id}")
    public void deleteRecipe(@PathVariable Long id) {
        dbService.deleteRecipe(id);
    }
}
