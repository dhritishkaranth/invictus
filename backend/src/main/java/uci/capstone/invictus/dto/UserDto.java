package uci.capstone.invictus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDto {
    private String firstName;

    private String secondName;

    private List<String> languages;

    private String location;

    private String typeOfSeeker;

    private String typeOfIllness;

    private boolean anonymous;

    public UserDto(String firstName, String secondName, List<String> languages, String location, String typeOfSeeker, String typeOfIllness, boolean anonymous) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.languages = languages;
        this.location = location;
        this.typeOfSeeker = typeOfSeeker;
        this.typeOfIllness = typeOfIllness;
        this.anonymous = anonymous;
    }
}

