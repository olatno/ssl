package com.gallery.ssl.repository;

import com.gallery.ssl.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Image repository class
 *
 * @author ola
 * @since 14/11/2020.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
