package uci.capstone.invictus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class GroupDto{

    private String groupName;

    private String location;

    private List<String> languages;

    private String typeOfIllness;

    private List<String> resources;

    public GroupDto(String groupName, String location, List<String> languages, String typeOfIllness, List<String> resources) {
        this.groupName = groupName;
        this.location = location;
        this.languages = languages;
        this.typeOfIllness = typeOfIllness;
        this.resources = resources;
    }
}
