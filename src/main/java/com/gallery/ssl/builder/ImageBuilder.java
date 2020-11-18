package com.gallery.ssl.builder;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;

import java.time.LocalDate;

/**
 * Builder class for User
 *
 * @author ola
 * @since  15/11/2020.
 */

public final class ImageBuilder {

    private Integer id;
    private String name;
    private String description;
    private LocalDate createdDate;
    private Byte[] data;
    private Gallery gallery;


    private ImageBuilder() {
    }

    public static ImageBuilder aImage() {
        return new ImageBuilder();
    }

    public ImageBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ImageBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ImageBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ImageBuilder withCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ImageBuilder withData(Byte[] data) {
        this.data = data;
        return this;
    }

    public ImageBuilder withGallery(Gallery gallery) {
        this.gallery = gallery;
        return this;
    }

    public Image build() {
        Image image = new Image();
        image.setGallery(gallery);
        image.setCreatedDate(createdDate);
        image.setData(data);
        image.setName(name);
        image.setName(description);
        image.setId(id);
        return image;
    }
}