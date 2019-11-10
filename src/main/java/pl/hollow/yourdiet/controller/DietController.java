package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.DietDto;
import pl.hollow.yourdiet.mapper.DietMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("diets")
public class DietController {

    @Autowired
    private DbService dbService;

    @Autowired
    private DietMapper dietMapper;

    @GetMapping()
    public List<DietDto> getDiets() {
        return dietMapper.mapToDietDtoList(dbService.getDiets());
    }

    @GetMapping("{id}")
    public DietDto getDiet(@PathVariable Long id) {
        return dietMapper.mapToDietDto(dbService.getDiet(id).orElseThrow());
    }

    @PostMapping()
    public void createDiet(@RequestBody DietDto dietDto) {
        dbService.saveDiet(dietMapper.mapToDiet(dietDto));
    }

    @PutMapping()
    public DietDto updateDiet(@RequestBody DietDto dietDto) {
        return dietMapper.mapToDietDto(dbService.saveDiet(dietMapper.mapToDiet(dietDto)));
    }

    @DeleteMapping("{id}")
    public void deleteDiet(@PathVariable Long id) {
        dbService.deleteDiet(id);
    }
}
