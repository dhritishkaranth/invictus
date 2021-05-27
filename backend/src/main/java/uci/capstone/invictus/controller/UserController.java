package uci.capstone.invictus.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uci.capstone.invictus.dto.UserDto;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.service.UserService;
import uci.capstone.invictus.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invictus/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAllUsers();

        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(convertToEntity(userDto));
    }

    @GetMapping("/firstname/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByFirstName(@PathVariable String name) {
        List<User> users = userService.findUsersByFirstName(name);

        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/secondname/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersBySecondName(@PathVariable String name) {
        List<User> users = userService.findUsersBySecondName(name);

        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/location/{location}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByLocation(@PathVariable String location) {
        List<User> users = userService.findUsersByLocation(location);

        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/typeofseeker/{seeker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByTypeOfSeeker(@PathVariable String seeker) {
        List<User> users = userService.findUsersByTypeOfSeeker(Constants.Seeker.valueOf(seeker));

        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/languages/{language}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByLanguages(@PathVariable String language) {
        List<User> users = userService.findUsersByLanguage(language);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/typeofillness/{illness}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByTypeOfIllness(@PathVariable String illness) {
        List<User> users = userService.findUsersByTypeOfIllness(illness);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/anonymous/{flag}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByAnonymous(@PathVariable boolean flag) {
        List<User> users = userService.findUsersByAnonymity(flag);
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/age/{age}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByAge(@PathVariable int age) {
        List<User> users = userService.findUsersByAge(age);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/illness/{illness}/seeker/{seeker}/language/{language}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByIllnessLanguageSeeker(@PathVariable String illness, @PathVariable String seeker,  @PathVariable String language) {
        List<User> users = userService.findByIllnessAndLanguagesAndSeeker(illness, language, seeker);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/illness/{illness}/seeker/{seeker}/language/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByIllnessAnyLanguageSeeker(@PathVariable String illness, @PathVariable String seeker) {
        List<User> users = userService.findByIllnessAndSeeker(illness, seeker);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/illness/{illness}/seeker/{seeker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByIllnessSeeker(@PathVariable String illness, @PathVariable String seeker) {
        List<User> users = userService.findByIllnessAndSeeker(illness, seeker);
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/gender/{gender}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsersByGender(@PathVariable String gender) {
        List<User> users = userService.findUsersByGender(Constants.Gender.valueOf(gender));
        return users.stream()
                .filter(user -> !user.isAnonymous())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateUser(@RequestBody UserDto userDto){
        userService.update(convertToEntity(userDto));
    }

    @GetMapping("/aggregator/seeker")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HashMap<String, Integer> getUsersBySeeker() {
       return userService.findSeekerBasedUserCounts();
    }

    @GetMapping("/aggregator/illness")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HashMap<String, Integer> getUsersByIllness() {
        return userService.findIllnessBasedCounts();
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUsersByIllness(@PathVariable String username) {
         return convertToDto(userService.findByUsername(username));
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}