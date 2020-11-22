package com.gallery.ssl.controller;

import com.gallery.ssl.model.User;
import com.gallery.ssl.security.LoginUserDetailService;
import com.gallery.ssl.security.LoginUserDetails;
import com.gallery.ssl.service.GalleryService;
import com.gallery.ssl.util.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    @Autowired
    LoginUserDetailService loginUserDetailService;

    /**
     * Login request url
     *
     * @param model the model use to pass info to front end
     * @param request the HttpServletRequest object
     *
     * @return String login
     */
    @RequestMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) {
        User userObj = (User)request.getSession().getAttribute("userObj");
        String firstName = "";
        if(userObj != null) {
            firstName = userObj.getFirstName();
        }
        String message = "please login into your gallery";
        String loginMgs = StringUtils.isNoneEmpty(firstName) ? firstName +" "+message : message;
        model.addAttribute("loginMgs", loginMgs);
        request.getSession().removeAttribute("userObj");
       return "login" ;
    }

    /**
     * Admin request url
     * @param model the model use to pass info to front end
     *
     * @return String admin
     */
    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        String admin = StringUtils.EMPTY;
        LoginUserDetails loginUserDetails = loginUserDetailService.getLoginUserDetails();
        User user = null;
        if(loginUserDetails != null) {
            user = loginUserDetails.getUser();
            model.addAttribute("user", user);
            admin = "admin";
        }
        if(user == null){
            admin =  "redirect:/";
        }
        return admin;
    }

    /**
     * Registration request url
     *
     * @param model the model use to pass info to front end
     * @param request the HttpServletRequest object
     *
     * @return String registration
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String registration(Model model, HttpServletRequest request, @ModelAttribute("registerRequest") RegisterRequest registerRequest) {
        User userObj = null;
        if(StringUtils.equals(request.getMethod(), RequestMethod.POST.name())) {
            userObj = galleryService.user(registerRequest);
        }
        if(userObj != null && StringUtils.isNoneEmpty(userObj.getEmail())) {
            model.addAttribute("userObj", userObj);
            request.getSession().setAttribute("userObj", userObj );
            return "redirect:/login";
        }
        return "registration";
    }

    /**
     * The gallery model view
     *
     * @param model the model use to pass info to front end
     *
     * @return List of map
     */
    @RequestMapping(value = "/gallery")
    public String gallery(Model model) {
        List<Map<String , Object>> imageMapList = galleryService.getGallery();
        model.addAttribute("imageGallery", imageMapList);
        return "gallery";
    }
}
