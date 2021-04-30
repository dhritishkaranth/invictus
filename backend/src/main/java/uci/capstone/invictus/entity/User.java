package uci.capstone.invictus.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uci.capstone.invictus.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
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
    private Constants.Seeker typeOfSeeker;

    @Column(name = "typeofillness")

    private String typeOfIllness;

    @Enumerated(EnumType.STRING)
    private Constants.YesNo anonymous;

    public User() {
    }

    public User(Long id, String firstName, String secondName, String languages, String location, Constants.Seeker typeOfSeeker, String typeOfIllness, Constants.YesNo anonymous) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.languages = languages;
        this.location = location;
        this.typeOfSeeker = typeOfSeeker;
        this.typeOfIllness = typeOfIllness;
        this.anonymous = anonymous;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", languages='" + languages + '\'' +
                ", location='" + location + '\'' +
                ", typeOfSeeker=" + typeOfSeeker +
                ", typeOfIllness='" + typeOfIllness + '\'' +
                ", anonymous=" + anonymous +
                '}';
    }
}

