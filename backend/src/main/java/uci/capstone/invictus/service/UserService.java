package uci.capstone.invictus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.UserRepository;
import uci.capstone.invictus.utils.Constants;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {

        List<User> users = repository.findAll();
        if(users.isEmpty())
            throw new NoDataFoundException();
        return users;
    }

    public void createUser(User user){
        repository.save(user);
    }

    public List<User> findUsersByFirstName(String firstName){

        List<User> users = repository.findByFirstName(firstName);
        if(users.isEmpty())
            throw new UserNotFoundException("First Name", firstName);
        return users;
    }

    public List<User> findUsersBySecondName(String secondName){

        List<User> users = repository.findBySecondName(secondName);
        if(users.isEmpty())
            throw new UserNotFoundException("Second Name", secondName);
        return users;
    }

    public List<User> findUsersByLocation(String location){

        List<User> users = repository.findByLocation(location);
        if(users.isEmpty())
            throw new UserNotFoundException("Location", location);
        return users;
    }

    public List<User> findUsersByTypeOfSeeker(Constants.Seeker seeker){

        List<User> users = repository.findByTypeOfSeeker(seeker);
        if(users.isEmpty())
            throw new UserNotFoundException("Type Of Seeker", seeker.toString());
        return users;
    }

    public List<User> findUsersByLanguage(String language){

        List<User> users = repository.findByLanguages(language);
        if(users.isEmpty())
            throw new UserNotFoundException("Languages", language);
        return users;
    }

    public void update(User newUser){

        repository.findById(newUser.getId())
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setSecondName(newUser.getSecondName());
                    user.setAnonymous(newUser.isAnonymous());
                    user.setTypeOfSeeker(newUser.getTypeOfSeeker());
                    user.setLanguages(newUser.getLanguages());
                    user.setLocation(newUser.getLocation());
                    user.setTypeOfIllness(newUser.getTypeOfIllness());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    return repository.save(newUser);
                });
    }

}
