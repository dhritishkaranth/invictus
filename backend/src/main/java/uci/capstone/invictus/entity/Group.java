package uci.capstone.invictus.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Setter
@Getter
@ToString
@NoArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        )
})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "groupname")
    private String groupName;

    private String location;

    @Type(type = "list-array")
    @Column(
            name = "languages",
            columnDefinition = "text[]"
    )
    private List<String> languages;

    @Column(name = "typeofillness")
    private String typeOfIllness;

    @Type(type = "list-array")
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
