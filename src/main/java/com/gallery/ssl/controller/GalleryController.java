package com.gallery.ssl.controller;

import com.gallery.ssl.model.User;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The controller class for the mvc
 *
 * @author ola
 * @since 14/11/2020.
 */

@Controller
@ComponentScan(basePackages = "com.gallery.ssl")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String admin(Model model, RegisterRequest registerRequest) {
        User userObj = galleryService.user(registerRequest);
        model.addAttribute("userObj", userObj);
       return "admin" ;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration( @ModelAttribute("registerRequest") RegisterRequest registerRequest) {
        return "registration" ;
    }
}
