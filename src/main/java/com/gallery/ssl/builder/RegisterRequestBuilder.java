package com.gallery.ssl.builder;

import com.gallery.ssl.util.RegisterRequest;

/**
 * Builder class for User
 *
 * @author ola
 * @since  15/11/2020.
 */

public final class RegisterRequestBuilder {

    private String firstName;
    private String lastName;
    private String title;
    private String password;
    private String email;


    private RegisterRequestBuilder() {
    }

    public static RegisterRequestBuilder aRegisterRequest() {
        return new RegisterRequestBuilder();
    }

    public RegisterRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RegisterRequest build() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setTitle(title);
        registerRequest.setLastName(lastName);
        registerRequest.setFirstName(firstName);
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        return registerRequest;
    }
}