package com.gallery.ssl.exception;

import org.springframework.http.HttpStatus;

/**
 * GalleryServiceException class
 *
 * @author ola
 * @since 14/11/2020.
 */
public class GalleryServiceException extends RuntimeException {
    private HttpStatus code;

    public GalleryServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public GalleryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Get code
     *
     * @return the code
     */
    public HttpStatus getCode() {
        return this.code;
    }
}