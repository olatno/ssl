package com.gallery.ssl.repository;

import com.gallery.ssl.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Image repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
