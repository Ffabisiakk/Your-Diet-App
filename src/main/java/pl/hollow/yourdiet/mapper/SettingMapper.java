package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.SettingDto;
import pl.hollow.yourdiet.entity.Setting;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SettingMapper {

    public SettingDto mapToSettingDto(Setting setting) {
        return new SettingDto();
    }

    public Setting mapToSetting(SettingDto settingDto) {
        return new Setting();
    }

    public List<SettingDto> mapToSettingDtoList(List<Setting> settings) {
        return settings.stream()
                .map(this::mapToSettingDto)
                .collect(Collectors.toList());
    }
}
