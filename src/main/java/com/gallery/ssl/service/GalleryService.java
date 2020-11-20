package com.gallery.ssl.service;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.util.RegisterRequest;

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

    /**
     * The List of  gallery
     *
     * @return List of uploaded images by all users
     */
    List<Image> viewGallery();

    /**
     * The delete specific image
     * @param id the image id
     *
     * @return void
     */
    void deleteImage(Integer id);

    /**
     * The delete all images
     *
     * @return void
     */
    void deleteGallery();

    /**
     * Upload image method for users
     *
     * @param data the image uploaded in byte array
     * @param name the image name
     * @param description the image description
     * @param user the login user
     *
     * @return uploaded image
     */
    Image uploadImage(byte[] data, String name, String description, User user);

    String editImage();

    /**
     * The List of user's gallery
     *
     * @return List of uploaded images by a user
     */
    List<Object[]> userGallery(User user);
}
