package uci.capstone.invictus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uci.capstone.invictus.utils.Constants;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String firstName;

    private String secondName;

    private String username;

    private List<String> languages;

    private String location;

    private String typeOfSeeker;

    private String typeOfIllness;

    private boolean anonymous;

    private int age;

    private Constants.Gender gender;

    private List<String> resources;

    private String password;

    private String role;
}

