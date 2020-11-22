package com.gallery.ssl.service;

import com.gallery.ssl.exception.GalleryServiceException;
import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.util.EditImageRequest;
import com.gallery.ssl.util.RegisterRequest;

import java.util.List;
import java.util.Map;

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
     * @throws GalleryServiceException
     * @return the register user
     */
    User findByEmail(String email) throws GalleryServiceException;

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
    List<Image> viewImages();

    /**
     * The List of  gallery
     *
     * @return List of uploaded images by all users
     */
    List<Gallery> viewGallery();

    /**
     * The List of gallery map
     *
     * @return List of uploaded images by all users
     */
    List<Map<String , Object>> getGallery();

    /**
     * The delete specific image
     *
     * @param id the image id
     *@throws GalleryServiceException
     * @return void
     */
    void deleteImage(Integer id) throws GalleryServiceException;

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

    /**
     * Edit image name or description
     *
     * @param editImageRequest
     * @throws GalleryServiceException
     * @return void
     */
    void editImage(EditImageRequest editImageRequest) throws GalleryServiceException;

    /**
     * The List of user's gallery
     *
     * @param user
     * @return List of uploaded images by a user
     */
    List<Object[]> userGallery(User user);
}
