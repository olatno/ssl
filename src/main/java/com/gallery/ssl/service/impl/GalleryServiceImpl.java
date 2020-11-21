package com.gallery.ssl.service.impl;

import com.gallery.ssl.exception.GalleryServiceException;
import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.ImageRepository;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.BytesProcess;
import com.gallery.ssl.util.EditImageRequest;
import com.gallery.ssl.util.RegisterRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The gallery service use by the controller
 *
 * @author ola
 * @since 14/11/2020.
 */

@Service
public class GalleryServiceImpl implements GalleryService {

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
    public User findByEmail(String email) throws GalleryServiceException{
        if(StringUtils.isEmpty(email)){
            throw new GalleryServiceException("Email should not be empty");
        }else {return userRepository.findByEmail(email);}
    }

    /**
     * {@inheritDoc}
     */
    public List<Image> viewGallery(){
        return imageRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public void deleteImage(Integer id) throws GalleryServiceException{
        if(id == null || id == 0){
            throw new GalleryServiceException("Image id must not be null or 0");
        }else {
            Optional<Image> image = imageRepository.findById(id);
            imageRepository.delete(image.get());
        }
    }

    /**
     * {@inheritDoc} deleteGallery
     */
    public void deleteGallery(){
       imageRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image uploadImage(byte[] data, String name, String description, User user)  {
        Image image = new Image();
        image.setName(name);
        image.setDescription(description);
        image.setCreatedDate(LocalDate.now());
        image.setGallery(user.getGallery());
        image.setData(BytesProcess.compressImageBytes(data));

        return imageRepository.save(image);
    }

    /**
     * {@inheritDoc}
     */
    public void editImage(EditImageRequest editImageRequest) throws GalleryServiceException{
        if(StringUtils.isEmpty(editImageRequest.getDescription()) || StringUtils.isEmpty(editImageRequest.getName())){
            throw new GalleryServiceException("Image name or Image description should not be emptied");
        }else{
            Optional<Image> image = imageRepository.findById(editImageRequest.getId());
            image.get().setName(editImageRequest.getName());
            image.get().setDescription(editImageRequest.getDescription());
            imageRepository.save(image.get());
        }
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
            Object[] objects = new Object[5];
            byte[] bytes = ArrayUtils.toPrimitive(image.getData());
            objects[0] = BytesProcess.decompressBytes(bytes);
            objects[1] = image.getName();
            objects[2] = image.getDescription();
            LocalDate localDate = image.getCreatedDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            objects[3] = localDate.format(formatter);
            objects[4] = image.getId();
            listObjects.add(objects);
        }
        return listObjects;
    }
}
