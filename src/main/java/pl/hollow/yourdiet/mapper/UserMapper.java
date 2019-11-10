package pl.hollow.yourdiet.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.UserDto;
import pl.hollow.yourdiet.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private DietMapper dietMapper;

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), dietMapper.mapToDietDtoList(user.getDiets()), user.getUserName(),
                user.getSex(), user.getAge(), user.getHeight(), user.getWeight());
    }

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), dietMapper.mapToDietList(userDto.getDiets()), userDto.getUserName(),
                userDto.getSex(), userDto.getAge(), userDto.getHeight(), userDto.getWeight());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
