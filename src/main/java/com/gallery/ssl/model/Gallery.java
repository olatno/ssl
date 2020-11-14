package com.gallery.ssl.model;

import javax.persistence.*;
import java.util.List;

/**
 * The Entity class for the gallery
 *
 * @author ola
 * @since 14/11/2020.
 */

@Entity
public class Gallery {

    private int id;
    private User user;
    private String title;
    List<Image> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    @OneToOne (cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   @OneToMany
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
