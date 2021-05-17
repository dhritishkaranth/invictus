package uci.capstone.invictus.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getTypeOfIllness() {
        return typeOfIllness;
    }

    public void setTypeOfIllness(String typeOfIllness) {
        this.typeOfIllness = typeOfIllness;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
