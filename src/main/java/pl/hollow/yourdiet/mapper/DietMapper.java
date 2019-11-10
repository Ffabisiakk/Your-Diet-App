package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.DietDto;
import pl.hollow.yourdiet.entity.Diet;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DietMapper {

    public DietDto mapToDietDto(Diet diet) {
        return new DietDto();
    }

    public Diet mapToDiet(DietDto dietDto) {
        return new Diet();
    }

    public List<DietDto> mapToDietDtoList(List<Diet> diets) {
        return diets.stream()
                .map(this::mapToDietDto)
                .collect(Collectors.toList());
    }
}
