package com.gallery.ssl.repository;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The Gallery repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("SELECT i FROM Image i WHERE i.gallery = :gallery")
    List<Image> findUserGallery(@Param("gallery") Gallery gallery);
}
