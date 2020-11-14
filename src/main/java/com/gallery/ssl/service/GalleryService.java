package com.gallery.ssl.service;

import com.gallery.ssl.model.User;

public interface GalleryService {

    String createdUser(User user); //form view, return to login page
    User getUserLoginByEmail(String email);//return user gallery with ability to upload, edit and delete image
    String viewGallery();// first view with all gallery images plus menu to login or create user
    String deleteImage();
    String uploadImage();
    String editImage();

}
