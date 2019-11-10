package pl.hollow.yourdiet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table
public class Meal implements Comparable<Meal> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Diet diet;

    private DayOfWeek dayOfWeek;
    private LocalTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(recipe, meal.recipe) &&
                dayOfWeek == meal.dayOfWeek &&
                Objects.equals(time, meal.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, dayOfWeek, time);
    }

    @Override
    public int compareTo(Meal meal) {
        int comparedDay = dayOfWeek.compareTo(meal.dayOfWeek);
        if (comparedDay == 0) {
            return time.compareTo(meal.time);
        } else {
            return comparedDay;
        }
    }
}
