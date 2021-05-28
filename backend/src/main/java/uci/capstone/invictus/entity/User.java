package uci.capstone.invictus.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import uci.capstone.invictus.utils.Constants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;

    private String username;

    @Type(type = "list-array")
    @Column(
            name = "languages",
            columnDefinition = "text[]"
    )
    private List<String> languages;

    private String location;

    @Column(name = "typeofseeker")
    @Enumerated(EnumType.STRING)
    private  Constants.Seeker typeOfSeeker;

    @Column(name = "typeofillness")
    private String typeOfIllness;

    private boolean anonymous;

    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Constants.Gender gender;

    @Type(type = "list-array")
    @Column(
            name = "resources",
            columnDefinition = "text[]"
    )
    private List<String> resources;

    private String password;

    private String role;
}

