package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.utils.Constants;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    List<User> findBySecondName(String secondName);
    List<User> findByLocation(String location);
    List<User> findByTypeOfSeeker(Constants.Seeker seeker);

    @Query(value = "SELECT * FROM users WHERE ?1=ANY(languages)", nativeQuery = true)
    List<User> findByLanguages(String language);
}