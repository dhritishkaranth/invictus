package uci.capstone.invictus.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uci.capstone.invictus.dto.UserDto;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.service.UserService;
import uci.capstone.invictus.utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invictus/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAllUsers();

        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(convertToEntity(userDto));
    }

    @GetMapping("/users/firstname/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByFirstName(@PathVariable String name) {
        List<User> users = userService.findUsersByFirstName(name);

        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/secondname/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersBySecondName(@PathVariable String name) {
        List<User> users = userService.findUsersBySecondName(name);

        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/location/{location}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByLocation(@PathVariable String location) {
        List<User> users = userService.findUsersByLocation(location);

        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/typeofseeker/{seeker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByTypeOfSeeker(@PathVariable String seeker) {
        List<User> users = userService.findUsersByTypeOfSeeker(Constants.Seeker.valueOf(seeker));

        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/languages/{language}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByLanguages(@PathVariable String language) {
        List<User> users = userService.findUsersByLanguage(language);
        return users.stream()
                .filter(user -> !user.getAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}