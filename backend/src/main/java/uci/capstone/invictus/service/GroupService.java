package uci.capstone.invictus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.GroupNotFoundException;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.exception.RepositoryException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.GroupRepository;
import uci.capstone.invictus.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group findByGroupName(String groupName){
        return groupRepository.findByGroupName(groupName).orElseThrow(()->new GroupNotFoundException("group name", groupName));
    }

    public List<Group> findAllGroups(){
        List<Group> groups = groupRepository.findAll();
        if(groups.isEmpty())
            throw new NoDataFoundException();
        return groups;
    }

    public List<Group> findByGroupLanguage(String language) {
        List<Group> groups = groupRepository.findByLanguages(language);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("group language", language);
        }
        return groups;
    }

    public void save(Group group){
        try {
            groupRepository.save(group);
        }
        catch (Exception e){
            throw new RepositoryException(e.getLocalizedMessage());
        }
    }

    public List<Group> findMatchedGroups(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Name", username));

        String languages = user.getLanguages().stream().collect(Collectors.joining(",", "{", "}"));

        List<Group> groups = groupRepository.findBestMatchedGroups(user.getTypeOfIllness(), languages, user.getLocation());

        if (groups.isEmpty())
            groups = groupRepository.findByTypeOfIllnessAndLocationOrLanguages(user.getTypeOfIllness(), languages, user.getLocation());

        if (groups.isEmpty())
            throw new GroupNotFoundException("No groups available for the user", user.getFirstName());

        return groups;
    }

    public List<Group> findByTypeOfIllnessAndLocation(String illness, String location){
        List<Group> groups = groupRepository.findByTypeOfIllnessAndLocation(illness, location);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness:" + illness + "and", "Location:" + location);
        }
        return groups;
    }

    public List<Group> findByIllness(String illness){
        List<Group> groups = groupRepository.findByTypeOfIllness(illness);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness", illness);
        }
        return groups;
    }

    public List<Group> findByLocation(String location){
        List<Group> groups = groupRepository.findByLocation(location);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("Location", location);
        }
        return groups;
    }

    public List<Group> findByIllnessAndLocation(String illness, String location){
        List<Group> groups = groupRepository.findByTypeOfIllnessAndLocation(illness, location);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness-" + illness, "Location-" + location);
        }
        return groups;
    }

    public List<Group> findByIllnessAndLanguage(String illness, String language){
        List<Group> groups = groupRepository.findByIllnessAndLanguage(illness, language);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness-" + illness , "Language-" + language);
        }
        return groups;
    }

    public HashMap<String, Integer> findIllnessBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        groupRepository.findTotalNumberOfGroupsByIllness()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public HashMap<String, Integer> findLocationBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        groupRepository.findTotalNumberOfGroupsByLocation()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public void update(Group newGroup) {

        groupRepository.findByGroupName(newGroup.getGroupName())
                .map(group -> {
                    group.setLanguages(newGroup.getLanguages());
                    group.setLocation(newGroup.getLocation());
                    group.setTypeOfIllness(newGroup.getTypeOfIllness());
                    group.setResources(newGroup.getResources());

                    return groupRepository.save(group);
                })
                .orElseGet(() -> {
                    return groupRepository.save(newGroup);
                });
    }
}
