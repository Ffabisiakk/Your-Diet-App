package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.hollow.yourdiet.dto.UserDto;
import pl.hollow.yourdiet.mapper.UserMapper;
import pl.hollow.yourdiet.service.DbService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private DbService dbService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(dbService.getUsers());
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.mapToUserDto(dbService.getUser(id).orElseThrow());
    }

    @PostMapping()
    public void createUser(UserDto userDto) {
        dbService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping()
    public UserDto updateUser(UserDto userDto) {
        return userMapper.mapToUserDto(dbService.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        dbService.deleteUser(id);
    }
}
