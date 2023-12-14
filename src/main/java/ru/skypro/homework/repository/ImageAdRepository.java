package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.ImageAd;

/**
 * Repository for getting methods to work with ad's image database
 */

@Repository
public interface ImageAdRepository extends CrudRepository<ImageAd, String> {
}
