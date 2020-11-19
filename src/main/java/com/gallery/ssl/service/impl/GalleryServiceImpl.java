package com.gallery.ssl.service.impl;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.GalleryRepository;
import com.gallery.ssl.repository.ImageRepository;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.BytesProcess;
import com.gallery.ssl.util.RegisterRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The gallery service use by the controller
 *
 * @author ola
 * @since 14/11/2020.
 */

@Service
public class GalleryServiceImpl implements GalleryService {

    private static final Logger logger = LogManager.getLogger(GalleryServiceImpl.class);

    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    public List<Image> viewGallery(){
        return imageRepository.findAll();
    }

    public String deleteImage(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image uploadImage(byte[] data, String name, String description, User user) {
        Image image = new Image();
        image.setName(name);
        image.setDescription(description);
        image.setCreatedDate(LocalDate.now());
        image.setGallery(user.getGallery());
        image.setData(BytesProcess.compressImageBytes(data));

        return imageRepository.save(image);
    }

    public String editImage(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gallery saveGallery(RegisterRequest registerRequest) {
        Gallery gallery = new Gallery();
        gallery.setTitle(registerRequest.getTitle());
        return gallery;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object[]> userGallery(User user){
        List<Image> images = imageRepository.findUserGallery(user.getGallery());
        List<Object[]> listObjects = new ArrayList<>();
        for(Image image : images){
            Object[] objects = new Object[4];
            byte[] bytes = ArrayUtils.toPrimitive(image.getData());
            objects[0] = BytesProcess.decompressBytes(bytes);
            objects[1] = image.getName();
            objects[2] = image.getDescription();
            LocalDate localDate = image.getCreatedDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            objects[3] = localDate.format(formatter);
            listObjects.add(objects);
        }
        return listObjects;
    }
}
