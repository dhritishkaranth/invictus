package uci.capstone.invictus.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uci.capstone.invictus.dto.GroupDto;
import uci.capstone.invictus.dto.UserDto;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.service.GroupService;
import uci.capstone.invictus.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invictus/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupService.findAllGroups();

      return groups.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
    }

    @GetMapping("/name/{groupname}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GroupDto getGroupByName(@PathVariable String groupname) {
        return convertToDto(groupService.findByGroupName(groupname));
    }

    @GetMapping("/languages/{lang}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GroupDto> getGroupByLanguage(@PathVariable String lang) {

        List<Group> groups = groupService.findByGroupLanguage(lang);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveGroup(@RequestBody GroupDto groupDto){
        groupService.save(convertToEntity(groupDto));
    }

    @GetMapping("/bestmatch/{username}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> getMatchedGroups(@PathVariable String username){
        List<Group> groups = groupService.findMatchedGroups(username);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/illness/{illness}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByIllness(@PathVariable String illness){
        List<Group> groups = groupService.findByIllness(illness);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/location/{location}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByLocation(@PathVariable String location){
        List<Group> groups = groupService.findByLocation(location);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/aggregator/illness")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Integer> findGroupsByIllness(){
        return groupService.findIllnessBasedCounts();
    }

    @GetMapping("/aggregator/location")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Integer> findGroupsByLocation(){
        return groupService.findLocationBasedCounts();
    }

    @GetMapping("illness/{illness}/location/{location}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByIllnessAndLocation(@PathVariable String illness, @PathVariable String location){
        List<Group> groups = groupService.findByIllnessAndLocation(illness, location);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("illness/{illness}/language/{language}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByIllnessAndLanguage(@PathVariable String illness, @PathVariable String language){
        List<Group> groups = groupService.findByIllnessAndLanguage(illness, language);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("illness/{illness}/language/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByIllnessAndAnyLanguage(@PathVariable String illness){
        List<Group> groups = groupService.findByIllness(illness);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("illness/{illness}/location/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findGroupByIllnessAndAnyLocation(@PathVariable String illness){
        List<Group> groups = groupService.findByIllness(illness);
        return groups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateGroup(@RequestBody GroupDto groupDto){
        groupService.update(convertToEntity(groupDto));
    }
    private GroupDto convertToDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

    private Group convertToEntity(GroupDto groupDto){
        return modelMapper.map(groupDto, Group.class);
    }
}
