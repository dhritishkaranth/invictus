package uci.capstone.invictus.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uci.capstone.invictus.dto.GroupDto;
import uci.capstone.invictus.dto.UserDto;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.service.GroupService;
import uci.capstone.invictus.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invictus/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupService.findAllGroups();

      return groups.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
    }

    @GetMapping("/groups/name/{groupname}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GroupDto getGroupByName(@PathVariable String groupname) {
        return convertToDto(groupService.findByGroupName(groupname));
    }

    @GetMapping("/groups/language/{lang}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GroupDto> getGroupByLanguage(@PathVariable String lang) {

        List<Group> groups = groupService.findByGroupLanguage(lang);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private GroupDto convertToDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

    private Group convertToEntity(GroupDto groupDto){
        return modelMapper.map(groupDto, Group.class);
    }
}
