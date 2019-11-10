package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.SettingDto;
import pl.hollow.yourdiet.entity.Setting;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SettingMapper {

    @Autowired
    private UserMapper userMapper;

    public SettingDto mapToSettingDto(Setting setting) {
        return new SettingDto(setting.getId(), userMapper.mapToUserDto(setting.getUser()),
                setting.getActivityLevel(), setting.getDietType(), setting.getNumberOfMeals(),
                setting.getExcludedIngredients(), setting.getTargetWeight(), setting.getVariationOfCalories(),
                setting.getTimeOfFirstMeal());
    }

    public Setting mapToSetting(SettingDto settingDto) {
        return new Setting(settingDto.getId(), userMapper.mapToUser(settingDto.getUser()),
                settingDto.getActivityLevel(), settingDto.getDietType(), settingDto.getNumberOfMeals(),
                settingDto.getExcludedIngredients(), settingDto.getTargetWeight(), settingDto.getVariationOfCalories(),
                settingDto.getTimeOfFirstMeal());
    }

    public List<SettingDto> mapToSettingDtoList(List<Setting> settings) {
        return settings.stream()
                .map(this::mapToSettingDto)
                .collect(Collectors.toList());
    }
}
