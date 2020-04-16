package main.repository;

import main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Adam Doliński
 * 09.04.2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUserLogin(String userLogin);
    Optional<User> findUserByUserLogin(String userLogin);

}
