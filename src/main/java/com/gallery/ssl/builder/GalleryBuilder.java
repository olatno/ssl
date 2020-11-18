package com.gallery.ssl.builder;

import com.gallery.ssl.model.Gallery;
import com.gallery.ssl.model.Image;

import java.util.Collection;

/**
 * Builder class for User
 *
 * @author ola
 * @since  15/11/2020.
 */

public final class GalleryBuilder {

    private Integer id;
    private String title;
    Collection<Image> images;


    private GalleryBuilder() {
    }

    public static GalleryBuilder aGallery() {
        return new GalleryBuilder();
    }

    public GalleryBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public GalleryBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public GalleryBuilder withImages(Collection<Image> images) {
        this.images = images;
        return this;
    }


    public Gallery build() {
        Gallery gallery = new Gallery();
        gallery.setImages(images);
        gallery.setTitle(title);
        gallery.setId(id);
        return gallery;
    }
}