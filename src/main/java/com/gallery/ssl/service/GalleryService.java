package com.gallery.ssl.service;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.util.RegisterRequest;

import java.io.IOException;
import java.util.List;

public interface GalleryService {

    /**
     * Save register user to db
     *
     * @param registerRequest the request object from frontend
     * @return the register user
     */
    User user(RegisterRequest registerRequest);

    /**
     * Find user by email
     *
     * @param email the user email address
     * @return the register user
     */
    User findByEmail(String email);

    /**
     * Save register user gallery to db
     *
     * @param registerRequest the request object from frontend
     * @return the register user gallery
     */
    Gallery saveGallery(RegisterRequest registerRequest);

    String viewGallery();// first view with all gallery images plus menu to login or create user
    String deleteImage();

    /**
     * Upload image method for users
     *
     * @param data the image uploaded in byte array
     * @param name the image name
     * @param description the image description
     * @param user the login user
     * @throws IOException
     * @return uploaded image
     */
    Image uploadImage(byte[] data, String name, String description, User user);
    String editImage();

    /**
     * The List of user's gallery
     *
     * @return List of uploaded images bu user
     */
    List<Object[]> userGallery(User user);
}
