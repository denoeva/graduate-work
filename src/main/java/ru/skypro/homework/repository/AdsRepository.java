package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for working with ads through the database
 */

@Repository
public interface AdsRepository extends CrudRepository<Ad, Integer> {
    @Query(value = "SELECT * FROM ads", nativeQuery = true)
    List<Ad> findAllAds();

    @Query(value = "SELECT * FROM ads " +
            "WHERE user_id = :meId", nativeQuery = true)
    List<Ad> getAdsMe(Integer meId);

    Optional<Ad> findAdByPk(int pk);

    Optional<Ad> findAdByTitle(String title);
}
