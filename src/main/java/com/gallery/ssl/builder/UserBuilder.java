package com.gallery.ssl.builder;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.User;

/**
 * Builder class for User
 *
 * @author ola
 * @since  15/11/2020.
 */

public final class UserBuilder {

    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gallery gallery;


    private UserBuilder() {
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withGallery(Gallery gallery) {
        this.gallery = gallery;
        return this;
    }

    public User build() {
        User user = new User();
        user.setGallery(gallery);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(id);
        return user;
    }
}