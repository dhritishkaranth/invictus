package uci.capstone.invictus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uci.capstone.invictus.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}