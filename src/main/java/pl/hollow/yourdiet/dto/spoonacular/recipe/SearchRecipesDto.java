package pl.hollow.yourdiet.dto.spoonacular.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(value = "true")
public class SearchRecipesDto {
    private int offset;
    private int number;
    @JsonProperty("results")
    private List<SpoonacularRecipeDto> recipes;
}
