package com.gallery.ssl.controller;

import com.gallery.ssl.model.User;
import com.gallery.ssl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * The controller class for the mvc
 *
 * @author ola
 * @since 14/11/2020.
 */

@Controller
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String admin(Model model, HttpServletRequest request, @ModelAttribute("user") User user) {
        galleryService.createdUser(user);
       return "admin" ;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration(Model model, HttpServletRequest request, @ModelAttribute("user") User user) {
        return "registration" ;
    }
}
