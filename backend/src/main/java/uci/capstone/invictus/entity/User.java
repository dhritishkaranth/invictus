package uci.capstone.invictus.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uci.capstone.invictus.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;
    private String languages;

    private String location;

    @Column(name = "typeofseeker")
    @Enumerated(EnumType.STRING)
    private  Constants.Seeker typeOfSeeker;

    @Column(name = "typeofillness")
    private String typeOfIllness;

    private boolean anonymous;

    public User() {
    }

    public User(Long id, String firstName, String secondName, String languages, String location, Constants.Seeker typeOfSeeker, String typeOfIllness, boolean anonymous) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.languages = languages;
        this.location = location;
        this.typeOfSeeker = typeOfSeeker;
        this.typeOfIllness = typeOfIllness;
        this.anonymous = anonymous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public  Constants.Seeker getTypeOfSeeker() {
        return typeOfSeeker;
    }

    public void setTypeOfSeeker( Constants.Seeker typeOfSeeker) {
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

