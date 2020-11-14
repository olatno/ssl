package com.gallery.ssl.repository;

import com.gallery.ssl.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Gallery repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
}
