package com.gallery.ssl.repository;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The User repository class
 *
 * @author ola
 * @since 14/11/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.gallery = :gallery")
    User findUserByGallery(@Param("gallery") Gallery gallery);
}
