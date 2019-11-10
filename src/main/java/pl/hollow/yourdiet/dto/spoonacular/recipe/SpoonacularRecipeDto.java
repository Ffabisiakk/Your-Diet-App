package pl.hollow.yourdiet.dto.spoonacular.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularRecipeDto {

    private long id;
    private String title;
    private String calories;
    private String carbs;
    private String fat;
    private String protein;

}
