package com.gallery.ssl.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * The Entity class for the gallery
 *
 * @author ola
 * @since 14/11/2020.
 */

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gallery", fetch= FetchType.EAGER)
    Collection<Image> images;

    public Gallery(){ }
    public Gallery( String title, Collection<Image> images){
        this.title = title;
        this.images = images;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }
}
