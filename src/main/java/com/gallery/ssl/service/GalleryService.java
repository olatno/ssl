package com.gallery.ssl.service;

import com.gallery.ssl.model.User;
import com.gallery.ssl.util.RegisterRequest;

public interface GalleryService {

    User user(RegisterRequest registerRequest); //form view, return to login page
    User findByEmail(String email);//return user gallery with ability to upload, edit and delete image
    String viewGallery();// first view with all gallery images plus menu to login or create user
    String deleteImage();
    String uploadImage();
    String editImage();

}
