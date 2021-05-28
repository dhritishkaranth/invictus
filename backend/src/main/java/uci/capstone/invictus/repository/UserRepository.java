package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.Pair;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.utils.Constants;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    List<User> findBySecondName(String secondName);
    List<User> findByLocation(String location);

    List<User> findByTypeOfIllness(String illness);
    List<User> findByTypeOfSeeker(Constants.Seeker seeker);
    List<User> findByAnonymous(boolean anonymity);
    List<User> findByAge(int age);
    Optional<User> findByUsername(String username);

    List<User> findByGender(Constants.Gender gender);

    @Query(value = "SELECT * FROM users WHERE ?1=ANY(languages)", nativeQuery = true)
    List<User> findByLanguages(String language);

    @Query(value = "SELECT typeofseeker as key, count(users.*) as value from users group by typeofseeker", nativeQuery = true)
    List<Pair> findTotalOfUsersBySeeker();

    @Query(value = "SELECT typeofillness as key, count(users.*) as value from users group by typeofillness order by value desc limit 5", nativeQuery = true)
    List<Pair> findTotalOfUsersByIllness();

    @Query(value = "SELECT * FROM users WHERE typeofillness=?1 AND typeofseeker=?3 AND CAST(?2 AS text[]) && languages", nativeQuery = true)
    List<User> findByIllnessAndLanguageAndTypeOfSeeker(String illness, String language, String seeker);

    List<User> findByTypeOfIllnessAndTypeOfSeeker(String illness, Constants.Seeker  seeker);

    @Query(value = "SELECT * FROM users WHERE typeofillness=?1 AND CAST(?2 AS text[]) && languages", nativeQuery = true)
    List<User> findByTypeOfIllnessAndLanguages(String illness, String language);


    @Query(value = "SELECT * FROM users WHERE typeofseeker=?1 AND CAST(?2 AS text[]) && languages", nativeQuery = true)
    List<User> findBySeekerAndLanguages(String seeker, String language);

}