package pl.hollow.yourdiet.dto;

import lombok.Getter;

@Getter
public class RecipeDto {

    private long id;
    private String title;
    private String calories;
    private String protein;
    private String carbs;
    private String fat;
}
