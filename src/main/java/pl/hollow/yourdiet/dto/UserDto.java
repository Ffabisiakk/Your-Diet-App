package pl.hollow.yourdiet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long id;
    private List<DietDto> diets;
    private String userName;
    private String sex;
    private int age;
    private int height;
    private int weight;
}
