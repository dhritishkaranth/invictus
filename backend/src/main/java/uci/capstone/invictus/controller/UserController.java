package uci.capstone.invictus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/invictus/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findCities() {
        return userService.findAll();
    }
}