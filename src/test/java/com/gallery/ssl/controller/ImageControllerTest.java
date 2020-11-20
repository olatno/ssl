package com.gallery.ssl.controller;

import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.security.LoginUserDetailService;
import com.gallery.ssl.security.LoginUserDetails;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.BytesProcess;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {

   @Mock
   private HttpServletRequest request;
   @Mock
   private MultipartFile file;
   @Mock
   private LoginUserDetailService loginUserDetailService;
   @Mock
   private GalleryService galleryService;
   @InjectMocks
   private ImageController imageController;

   @Test
   public void testSaveUploadedImage() throws IOException {
       Image image = mock(Image.class);
       SecurityContext context = mock(SecurityContext.class);
       SecurityContextHolder.setContext(context);
       Authentication authentication = mock(Authentication.class);
       when(context.getAuthentication()).thenReturn(authentication);
       LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
       User user = mock(User.class);
       when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(loginUserDetails);
       when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
       when(request.getParameter("name")).thenReturn("image name");
       when(request.getParameter("description")).thenReturn("image description");
       when(loginUserDetails.getUser()).thenReturn(user);
       when(galleryService.uploadImage(file.getBytes(), "image name", "image description", user)).thenReturn(image);
       ResponseEntity<List<Object[]>> responseEntity = imageController.saveUploadedImage(request,file);
       Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "OK");
       Assert.assertEquals(responseEntity.getStatusCode().value() , 200);
   }

   @Test
   public void testGetImages(){
       User user = mock(User.class);
       Image image = mock(Image.class);
       LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
       when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
       when(loginUserDetails.getUser()).thenReturn(user);
       ResponseEntity<List<Object[]>> responseEntity = imageController.getImages();
       Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "OK");
       Assert.assertEquals(responseEntity.getStatusCode().value() , 200);
   }

   @Test
   public void testDeleteImage(){
       User user = mock(User.class);
       LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
       when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
       when(loginUserDetails.getUser()).thenReturn(user);
       when( galleryService.userGallery(user)).thenReturn(getObjectList());
       ResponseEntity<List<Object[]>> responseEntity = imageController.deleteImage(user.getId());
       Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "OK");
       Assert.assertEquals(responseEntity.getStatusCode().value() , 200);
   }

   @Test
   public void testDeleteGallery(){
       User user = mock(User.class);
       LoginUserDetails loginUserDetails = mock(LoginUserDetails.class);
       when(loginUserDetailService.getLoginUserDetails()).thenReturn(loginUserDetails);
       when(loginUserDetails.getUser()).thenReturn(user);
       when( galleryService.userGallery(user)).thenReturn(getObjectList());
       ResponseEntity<List<Object[]>> responseEntity = imageController.deleteGallery();
       Assert.assertEquals(responseEntity.getStatusCode().getReasonPhrase(), "OK");
       Assert.assertEquals(responseEntity.getStatusCode().value() , 200);
   }

   private List<Object[]> getObjectList(){
       List<Object[]> listObjects = new ArrayList<>();
       Object[] objects = new Object[5];
       byte[] bytes  = new byte[4];
       bytes[0] = 10;
       bytes[0] = 20;
       bytes[0] = 20;
       bytes[0] = 10;
       objects[0] = BytesProcess.decompressBytes(bytes);
       objects[1] = "image name";
       objects[2] =  "image description";
       LocalDate localDate = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       objects[3] = localDate.format(formatter);
       objects[4] = 1;
       listObjects.add(objects);
       return listObjects;
   }
}
