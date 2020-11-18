package com.gallery.ssl.controller;


import com.gallery.ssl.model.Image;
import com.gallery.ssl.model.User;
import com.gallery.ssl.security.LoginUserDetailService;
import com.gallery.ssl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * The controller class for the rest
 *
 * @author ola
 * @since 16/11/2020.
 */

@Configuration
@ComponentScan(basePackages = "com.gallery.ssl")
@RestController
public class ImageController {

    @Autowired
    private GalleryService galleryService;
    @Autowired
    LoginUserDetailService loginUserDetailService;


    @RequestMapping(value = "/saveImage" , method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<List<Object[]>> saveUploadedImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        User user = loginUserDetailService.getLoginUserDetails().getUser();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Image image = galleryService.uploadImage(file.getBytes(), name, description, user);

        List<Object[]> imageList = galleryService.userGallery(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(imageList);
    }
}
