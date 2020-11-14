package com.gallery.ssl.service.impl;

import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.GalleryRepository;
import com.gallery.ssl.repository.ImageRepository;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The gallery service use by the controller
 *
 * @author ola
 * @since 14/11/2020.
 */

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String createdUser(User user) {
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Thank you " + user.getFirstName() + " " + user.getLastName() + ", login to upload image";
    }

    public User getUserLoginByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String viewGallery(){
        return null;
    }

    public String deleteImage(){
        return null;
    }

    public String uploadImage(){
        return null;
    }

    public String editImage(){
        return null;
    }
}
