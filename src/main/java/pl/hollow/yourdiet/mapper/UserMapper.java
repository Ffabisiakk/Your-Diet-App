package pl.hollow.yourdiet.mapper;

import org.springframework.stereotype.Component;
import pl.hollow.yourdiet.dto.UserDto;
import pl.hollow.yourdiet.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto();
    }

    public User mapToUser(UserDto userDto) {
        return new User();
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
