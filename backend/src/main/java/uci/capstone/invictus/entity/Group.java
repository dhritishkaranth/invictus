package uci.capstone.invictus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "groupname")
    private String groupName;

    private String location;

    @ElementCollection
    @Column(
            name = "languages",
            columnDefinition = "text[]"
    )
    private List<String> languages;

    @Column(name = "typeofillness")
    private String typeOfIllness;

    @ElementCollection
    @Column(
            name = "resources",
            columnDefinition = "text[]"
    )
    private List<String> resources;

    public Group(Long id, String groupName, String location, List<String> languages, String typeOfIllness, List<String> resources) {
        this.id = id;
        this.groupName = groupName;
        this.location = location;
        this.languages = languages;
        this.typeOfIllness = typeOfIllness;
        this.resources = resources;
    }
}
