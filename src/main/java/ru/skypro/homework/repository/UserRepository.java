package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Users;

import java.util.Optional;

/**
 * Repository for working with users through the database
 */
@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

}
