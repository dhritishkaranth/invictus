package uci.capstone.invictus.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.Pair;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.exception.RepositoryException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.UserRepository;
import uci.capstone.invictus.utils.Constants;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {

        List<User> users = repository.findAll();
        /*if(users.isEmpty())
            throw new NoDataFoundException();*/
        return users;
    }

    public void createUser(User user){
        try {
            repository.save(user);
        }
        catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }
    }

    public List<User> findUsersByFirstName(String firstName){

        List<User> users = repository.findByFirstName(firstName);
        /*if(users.isEmpty())
            throw new UserNotFoundException("First Name", firstName);*/
        return users;
    }

    public List<User> findUsersBySecondName(String secondName){

        List<User> users = repository.findBySecondName(secondName);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Second Name", secondName);*/
        return users;
    }

    public List<User> findUsersByLocation(String location){

        List<User> users = repository.findByLocation(location);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Location", location);*/
        return users;
    }

    public List<User> findUsersByTypeOfSeeker(Constants.Seeker seeker){

        List<User> users = repository.findByTypeOfSeeker(seeker);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Type Of Seeker", seeker.toString());*/
        return users;
    }

    public List<User> findUsersByLanguage(String language){

        List<User> users = repository.findByLanguages(language);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Languages", language);*/
        return users;
    }

    public List<User> findUsersByTypeOfIllness(String illness){

        List<User> users = repository.findByTypeOfIllness(illness);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Type of Illness", illness);*/
        return users;
    }

    public List<User> findUsersByAnonymity(boolean flag){

        List<User> users = repository.findByAnonymous(flag);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Anonymous", String.valueOf(flag));*/
        return users;
    }

    public List<User> findUsersByAge(int age){

        List<User> users = repository.findByAge(age);
       /* if(users.isEmpty())
            throw new UserNotFoundException("Age", String.valueOf(age));*/
        return users;
    }

    public List<User> findUsersByGender(Constants.Gender gender){

        List<User> users = repository.findByGender(gender);
       /* if(users.isEmpty())
            throw new UserNotFoundException("Gender", gender.toString());*/
        return users;
    }

    public HashMap<String, Integer> findSeekerBasedUserCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        repository.findTotalOfUsersBySeeker()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public List<User> findByIllnessAndLanguagesAndSeeker(String illness, String language, String seeker){
        List<User> users = repository.findByIllnessAndLanguageAndTypeOfSeeker(illness, "{" + language + "}", seeker);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Language-" + language + "Seeker-" + seeker);
        */
        return users;
    }

    public List<User> findByIllnessAndSeeker(String illness, String seeker){

        List<User> users = repository.findByTypeOfIllnessAndTypeOfSeeker(illness, Constants.Seeker.valueOf(seeker));
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Seeker-" + seeker);*/
        return users;
    }

    public User findByUsername(String username){
        Optional<User> user;
        try {
            user = repository.findByUsername(username);
        }
        catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }

        return user.orElseThrow(() -> new UserNotFoundException("username", username));
    }

    public HashMap<String, Integer> findIllnessBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        repository.findTotalOfUsersByIllness()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
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
