package com.gallery.ssl.security;

import com.gallery.ssl.builder.GalleryBuilder;
import com.gallery.ssl.builder.ImageBuilder;
import com.gallery.ssl.builder.RegisterRequestBuilder;
import com.gallery.ssl.builder.UserBuilder;
import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.repository.UserRepository;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.RegisterRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginUserDetailServiceTest {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private LoginUserDetailService loginUserDetailService;

    @Test
    public void testLoadUserByUsername(){
        User registerUser = savedUser("test@ymail.com");
       loginUserDetailService.loadUserByUsername("test@ymail.com");
        Assert.assertEquals(registerUser.getEmail(), "test@ymail.com");
    }

    @Test
    public void testGetLoginUserDetails(){
        SecurityContext context = mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        Authentication authentication = mock(Authentication.class);
        when(context.getAuthentication()).thenReturn(authentication);
        User user = this.getUser();
        LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(loginUserDetails);
        when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
        when(loginUserDetails.getUser()).thenReturn(user);
        Assert.assertEquals(user.getFirstName(), "John");
        Assert.assertEquals(user.getLastName(), "Dole");
        Assert.assertEquals(user.getGallery().getTitle(), "First gallery");
    }

    private User savedUser(String email){
        User saved = galleryService.user(this.getRegisterRequest(email));
        return userRepository.save(saved);
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
}
