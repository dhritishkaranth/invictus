package uci.capstone.invictus.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uci.capstone.invictus.authentication.MyUserDetails;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.UserRepository;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) throw new UserNotFoundException("username", username);

        return new MyUserDetails(user.get());
    }

}
