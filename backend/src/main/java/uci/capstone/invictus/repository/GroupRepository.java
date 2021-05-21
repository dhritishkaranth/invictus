package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT * FROM groups WHERE ?1=ANY(languages)", nativeQuery = true)
    List<Group> findByLanguages(String language);

    List<Group> findByTypeOfIllness(String illness);

    List<Group> findByLocation(String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 and languages && CAST(?2 AS text[]) and location=?3", nativeQuery = true)
    List<Group> findBestMatchedGroups(String illness, String languages, String location);

    List<Group> findByTypeOfIllnessAndLocation(String illness, String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 AND (languages && CAST(?2 AS text[]) OR location=?3)", nativeQuery = true)
    List<Group> findByTypeOfIllnessAndLocationOrLanguages(String illness, String languages, String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 AND ?2=ANY(languages)", nativeQuery = true)
    List<Group> findByIllnessAndLanguage(String illness, String languages);

}
