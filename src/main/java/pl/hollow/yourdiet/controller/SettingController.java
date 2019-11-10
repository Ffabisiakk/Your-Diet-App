package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.SettingDto;
import pl.hollow.yourdiet.mapper.SettingMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("settings")
public class SettingController {

    @Autowired
    private DbService dbService;

    @Autowired
    private SettingMapper settingMapper;

    @GetMapping()
    public List<SettingDto> getSettings() {
        return settingMapper.mapToSettingDtoList(dbService.getSettings());
    }

    @GetMapping("{id}")
    public SettingDto getSetting(@PathVariable Long id) {
        return settingMapper.mapToSettingDto(dbService.getSetting(id).orElse(null));
    }

    @PostMapping()
    public void createSetting(@RequestBody SettingDto settingDto) {
        dbService.saveSetting(settingMapper.mapToSetting(settingDto));
    }

    @PutMapping()
    public SettingDto updateSetting(@RequestBody SettingDto settingDto) {
        return settingMapper.mapToSettingDto(dbService.saveSetting(settingMapper.mapToSetting(settingDto)));
    }

    @DeleteMapping("{id}")
    public void deleteSetting(@PathVariable Long id) {
        dbService.deleteSetting(id);
    }
}
