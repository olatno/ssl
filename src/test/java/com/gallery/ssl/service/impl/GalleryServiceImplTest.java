package com.gallery.ssl.service.impl;

import com.gallery.ssl.builder.GalleryBuilder;
import com.gallery.ssl.builder.ImageBuilder;
import com.gallery.ssl.builder.RegisterRequestBuilder;
import com.gallery.ssl.builder.UserBuilder;
import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.EditImageRequest;
import com.gallery.ssl.util.RegisterRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GalleryServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GalleryService galleryService;

    @Test
    public void testUser(){
        User user = savedUser("tes21@ymail.com");;
        Assert.assertEquals(user.getFirstName(), "John");
        Assert.assertEquals(user.getLastName(), "Dole");
        Assert.assertEquals(user.getEmail(), "tes21@ymail.com");
        Assert.assertEquals(user.getGallery().getTitle(), "First gallery");
    }

    @Test
    public void testFindUserByEmail(){
        User user = savedUser("test5@gmail.com");;
        User userFind = galleryService.findByEmail(user.getEmail());
        Assert.assertEquals(user.getEmail(), userFind.getEmail());
    }

    @Test
    public void testUploadImage(){
        User user = savedUser("test4@gmail.com");;
        Image image = this.uploadImage(user);
        Assert.assertEquals(image.getName(), "Phone");
        Assert.assertEquals(image.getDescription(), "Samsung mobile phone");
        Assert.assertEquals(image.getGallery().getTitle(), "First gallery");
    }

    @Test
    public void testUserGallery(){
        User user = savedUser("test3@gmail.com");;
        Image savedImage = this.uploadImage(user);
        List<Object[]> objects = galleryService.userGallery(user);
        Assert.assertEquals(objects.size(), 1);
    }

    @Test
    public void testViewGallery(){
        User user = savedUser("test2@gmail.com");
        List<Gallery> images =  galleryService.viewGallery();
        Assert.assertEquals(images.size(),   1);
    }

    @Test
    public void testDeleteImage(){
        User user = savedUser("test1@gmail.com");
       Image savedImage = this.uploadImage(user);
       galleryService.deleteImage(savedImage.getId());
        Assert.assertEquals(savedImage.getName(), "Phone");
        Assert.assertEquals(savedImage.getDescription(), "Samsung mobile phone");
        Assert.assertEquals(savedImage.getGallery().getTitle(), "First gallery");
    }

    @Test
    public void testDeleteGallery(){
        User user = savedUser("test@gmail.com");
        Image savedImage = this.uploadImage(user);
        galleryService.deleteGallery();
        Assert.assertEquals(savedImage.getName(), "Phone");
        Assert.assertEquals(savedImage.getDescription(), "Samsung mobile phone");
        Assert.assertEquals(savedImage.getGallery().getTitle(), "First gallery");
    }

    private EditImageRequest getEditImageRequest(){
        EditImageRequest editImageRequest = new EditImageRequest();
        editImageRequest.setId(1);
        editImageRequest.setName("Image name");
        editImageRequest.setDescription("Image description");
        return editImageRequest;
    }

    private RegisterRequest getRegisterRequest(String email){
        return RegisterRequestBuilder.aRegisterRequest().
                withEmail(email).
                withFirstName("John").
                withLastName("Dole").
                withTitle(getGallery().getTitle()).
                withPassword("1234").
                build();
    }

    private Gallery getGallery(){
        return GalleryBuilder.aGallery().withId(1).
                withTitle("First gallery").
                withImages(Arrays.asList(getImage())).
                build();
    }

    private Image getImage(){
        return ImageBuilder.aImage().withId(1).
                withName("Phone").
                withDescription("Samsung mobile phone").
                withCreatedDate(LocalDate.parse("2020-01-08")).
                withData(null).
                //  withGallery(getGallery()).
                        build();
    }

    private User getUser(){
        return UserBuilder.aUser().withEmail("test@ymail.com").
                withFirstName("John").
                withLastName("Dole").
                withPassword("1234").
                withGallery(getGallery()).
                withId(1).
                build();
    }

    private User savedUser(String email){
        User saved = galleryService.user(this.getRegisterRequest(email));
        return userRepository.save(saved);
    }

    private Image uploadImage(User user){
        byte[] data = new byte[4];
        data[0] = 20;
        data[1] = 30;
        data[2] = 40;
        data[3] = 50;
        return galleryService.uploadImage(data, "Phone", "Samsung mobile phone", user);
    }
}
