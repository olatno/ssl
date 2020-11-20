package com.gallery.ssl.controller;

import com.gallery.ssl.builder.GalleryBuilder;
import com.gallery.ssl.builder.ImageBuilder;
import com.gallery.ssl.builder.RegisterRequestBuilder;
import com.gallery.ssl.builder.UserBuilder;
import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.security.LoginUserDetailService;
import com.gallery.ssl.security.LoginUserDetails;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.RegisterRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GalleryControllerTest {

    @Mock
    private Model model;
    @Mock
    private HttpServletRequest request;
    @Mock
    private SecurityContext context;
    @Mock
    private LoginUserDetailService loginUserDetailService;
    @Autowired
    private GalleryService galleryService;
    @InjectMocks
    GalleryController galleryController;

    @Test
    public void testLogin(){
        HttpSession httpSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        User user = this.getUser();
        when(httpSession.getAttribute(anyString())).thenReturn(user);
        galleryController.login(model, request);
        Assert.assertEquals(user.getFirstName(), "John");
        Assert.assertEquals(user.getLastName(), "Dole");
        Assert.assertEquals(user.getGallery().getTitle(), "First gallery");
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testRegistrationPost(){
        when(request.getMethod()).thenReturn("POST");
        User user = mock(User.class);
        RegisterRequest registerRequest = mock(RegisterRequest.class);
        when(galleryService.user(registerRequest)).thenReturn(user);
        galleryController.registration(model, request, this.getRegisterRequest());
    }

    @Test
    public void testRegistrationGet(){
        when(request.getMethod()).thenReturn("GET");
        galleryController.registration(model, request, null);
        verify(request, times(1)).getMethod();
    }

    @Test
    public void testAdminWithUser(){
        SecurityContext context = mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        Authentication authentication = mock(Authentication.class);
        when(context.getAuthentication()).thenReturn(authentication);
        User user = this.getUser();
        LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(loginUserDetails);
        when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
        when(loginUserDetails.getUser()).thenReturn(user);
        galleryController.admin(model);
        Assert.assertEquals(user.getFirstName(), "John");
        Assert.assertEquals(user.getLastName(), "Dole");
        Assert.assertEquals(user.getGallery().getTitle(), "First gallery");
    }

    @Test
    public void testAdminWithoutUser(){
        SecurityContext context = mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        Authentication authentication = mock(Authentication.class);
        when(context.getAuthentication()).thenReturn(authentication);
        User user = new User();
        LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(loginUserDetails);
        when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
        when(loginUserDetails.getUser()).thenReturn(null);
        galleryController.admin(model);
        Assert.assertEquals(user.getFirstName(), null);
        Assert.assertEquals(user.getLastName(), null);
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

    private RegisterRequest getRegisterRequest(){
        return RegisterRequestBuilder.aRegisterRequest().
                withEmail("tes21@ymail.com").
                withFirstName("John").
                withLastName("Dole").
                withTitle("Gallery title").
                withPassword("1234").
                build();
    }
}
