package uci.capstone.invictus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDto {
    private String firstName;

    private String secondName;
    private String languages;

    private String location;

    private String typeOfSeeker;

    private String typeOfIllness;

    private boolean anonymous;

    public UserDto(String firstName, String secondName, String languages, String location, String typeOfSeeker, String typeOfIllness, boolean anonymous) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.languages = languages;
        this.location = location;
        this.typeOfSeeker = typeOfSeeker;
        this.typeOfIllness = typeOfIllness;
        this.anonymous = anonymous;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeOfSeeker() {
        return typeOfSeeker;
    }

    public void setTypeOfSeeker(String typeOfSeeker) {
        this.typeOfSeeker = typeOfSeeker;
    }

    public String getTypeOfIllness() {
        return typeOfIllness;
    }

    public void setTypeOfIllness(String typeOfIllness) {
        this.typeOfIllness = typeOfIllness;
    }

    public boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}

