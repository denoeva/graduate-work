package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import java.util.List;

/**
 * Repository class for working with ads through the database
 */

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findAll();
}
