package com.gallery.ssl.service.impl;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.GalleryRepository;
import com.gallery.ssl.repository.ImageRepository;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.RegisterRequest;
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
    public User user(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setGallery(this.saveGallery(registerRequest));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
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

    public Gallery saveGallery(RegisterRequest registerRequest) {
        Gallery gallery = new Gallery();
        gallery.setTitle(registerRequest.getTitle());
        return gallery;
    }
}
