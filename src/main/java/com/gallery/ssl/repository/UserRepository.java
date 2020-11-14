package com.gallery.ssl.repository;

import com.gallery.ssl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The User repository class
 *
 * @author ola
 * @since 14/11/2020.
 */

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
