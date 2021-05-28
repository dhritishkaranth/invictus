package uci.capstone.invictus.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
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
}
