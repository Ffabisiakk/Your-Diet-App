package pl.hollow.yourdiet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table
public class Setting {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;
    @Enumerated(EnumType.STRING)
    private DietType dietType;
    private int numberOfMeals;
    private String excludedIngredients;
    private int targetWeight;
    private int variationOfCalories;
    private LocalTime timeOfFirstMeal;

    public enum ActivityLevel {
        INACTIVE, SEDENTARY, MODERATELY_ACTIVE, VIGOROUSLY_ACTIVE, EXTREMELY_ACTIVE
    }
    public enum DietType {
        GLUTEN_FREE("glutenFree"), KETOGENIC("ketogenic"), VEGETERIAN("vegetarian"), VEGAN("vegan"), PALEO("paleo");
        String s;
        DietType(String s){
            this.s = s;
        }
    }
}
