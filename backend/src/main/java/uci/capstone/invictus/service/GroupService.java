package uci.capstone.invictus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.exception.GroupNotFoundException;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public Group findByGroupName(String groupName){
        return repository.findByGroupName(groupName).orElseThrow(()->new GroupNotFoundException("group name", groupName));
    }

    public List<Group> findAllGroups(){
        List<Group> groups = repository.findAll();
        if(groups.isEmpty())
            throw new NoDataFoundException();
        return groups;
    }

    public List<Group> findByGroupLanguage(String language) {
        List<Group> groups = repository.findByLanguages(language);
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("group language", language);
        }
        return groups;
    }
}
