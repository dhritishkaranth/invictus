package uci.capstone.invictus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.UserRepository;

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

    public User findUserByFirstName(String firstName){

        return repository.findByFirstName(firstName)
                .orElseThrow(() -> new UserNotFoundException("First Name", firstName));
    }
}
