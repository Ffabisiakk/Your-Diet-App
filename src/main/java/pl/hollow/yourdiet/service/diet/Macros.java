package pl.hollow.yourdiet.service.diet;

import lombok.Getter;
import lombok.Setter;
import pl.hollow.yourdiet.entity.Setting;
import pl.hollow.yourdiet.entity.User;

@Getter
@Setter
public class Macros {
    private int kcal;
    private int whey;
    private int carbs;
    private int fat;

    Macros(User user, Setting setting) {
        this.kcal = calculateCaloriesDemand(user, setting);
        if (setting.getDietType() == Setting.DietType.KETOGENIC) {
            setWhey((int) (kcal * 0.25 / 4));
            setCarbs((int) (kcal * 0.05 / 4));
            setFat((int) (kcal * 0.7 / 9));
        } else {
            setWhey((int) (kcal * 0.3 / 4));
            setCarbs((int) (kcal * 0.5 / 4));
            setFat((int) (kcal * 0.2 / 9));
        }
    }

    public Macros(Macros macros, double percent) {
        this.kcal = (int) (macros.getKcal() * percent);
        this.whey = (int) (macros.getWhey() * percent);
        this.carbs = (int) (macros.getCarbs() * percent);
        this.fat = (int) (macros.getFat() * percent);
    }

    private int calculateCaloriesDemand(User user, Setting setting) {
        double basicDemand = 9.99 * user.getWeight() + 6.25 * user.getHeight() - 4.92 * user.getAge();
        if (user.getSex().equalsIgnoreCase("male")) {
            basicDemand += 5;
        } else {
            basicDemand -= 161;
        }
        switch (setting.getActivityLevel()) {
            case INACTIVE:
                basicDemand *= 1.2;
                break;
            case SEDENTARY:
                basicDemand *= 1.35;
                break;
            case MODERATELY_ACTIVE:
                basicDemand *= 1.55;
                break;
            case VIGOROUSLY_ACTIVE:
                basicDemand *= 1.75;
                break;
            case EXTREMELY_ACTIVE:
                basicDemand *= 2;
                break;
        }
        double totalDemand = basicDemand + setting.getVariationOfCalories();
        return (int) totalDemand;
    }
}
