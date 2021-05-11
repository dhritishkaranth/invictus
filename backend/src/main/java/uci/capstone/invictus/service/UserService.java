package uci.capstone.invictus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public void createUser(User user){
        repository.save(user);
    }

    public User findUserByFirstName(String firstName){

        Optional<User> user = repository.findByFirstName(firstName);
        if(user.isPresent()){
            return user.get();
        }
        return null;
   }
}
