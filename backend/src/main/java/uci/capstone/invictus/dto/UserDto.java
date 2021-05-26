package uci.capstone.invictus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uci.capstone.invictus.utils.Constants;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
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

    public UserDto(String firstName, String secondName, String username, List<String> languages, String location, String typeOfSeeker, String typeOfIllness, boolean anonymous, int age, Constants.Gender gender) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.languages = languages;
        this.location = location;
        this.typeOfSeeker = typeOfSeeker;
        this.typeOfIllness = typeOfIllness;
        this.anonymous = anonymous;
        this.age = age;
        this.gender = gender;
    }
}

