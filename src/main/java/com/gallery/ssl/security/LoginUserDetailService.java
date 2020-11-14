package com.gallery.ssl.security;

import com.gallery.ssl.model.User;
import com.gallery.ssl.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * The LoginUserDetailService class
 *
 * @author ola
 * @since 14/11/2020.
 */

@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private GalleryService galleryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = galleryService.getUserLoginByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        LoginUserDetails loginUserDetails = new LoginUserDetails();

        loginUserDetails.setUser(user);
        loginUserDetails.setAuthorities(authorities);

        return loginUserDetails;
    }

    public LoginUserDetails getLoginUserDetails() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUserDetails) {
            return (LoginUserDetails) principal;
        }
        return null;
    }
}
