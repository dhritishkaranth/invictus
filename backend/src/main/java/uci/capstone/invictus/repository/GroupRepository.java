package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByGroupName(String groupName);

    @Query(value = "SELECT * FROM groups WHERE ?1=ANY(languages)", nativeQuery = true)
    List<Group> findByLanguages(String language);
}
