package com.gallery.ssl.repository;

import com.gallery.ssl.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Gallery repository class
 *
 * @author ola
 * @since 14/11/2020.
 */
@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
}
