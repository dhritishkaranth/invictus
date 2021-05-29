package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.Pair;
import uci.capstone.invictus.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT * FROM groups WHERE languages && CAST(?1 AS text[])", nativeQuery = true)
    List<Group> findByLanguages(String language);

    List<Group> findByTypeOfIllness(String illness);

    List<Group> findByLocation(String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 and (languages && CAST(?2 AS text[])) and location=?3", nativeQuery = true)
    List<Group> findBestMatchedGroups(String illness, String languages, String location);

    List<Group> findByTypeOfIllnessAndLocation(String illness, String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 AND (languages && CAST(?2 AS text[]) OR location=?3)", nativeQuery = true)
    List<Group> findByTypeOfIllnessAndLocationOrLanguages(String illness, String languages, String location);

    @Query(value = "SELECT * FROM groups WHERE typeofillness=?1 AND (CAST(?2 AS text[]) && languages)", nativeQuery = true)
    List<Group> findByIllnessAndLanguage(String illness, String languages);

    @Query(value = "SELECT typeofillness as key, count(groups.*) as value from groups group by typeofillness order by value desc limit 5", nativeQuery = true)
    List<Pair> findTotalNumberOfGroupsByIllness();

    @Query(value = "SELECT location as key, count(groups.*) as value from groups group by location order by value desc limit 5", nativeQuery = true)
    List<Pair> findTotalNumberOfGroupsByLocation();
}
