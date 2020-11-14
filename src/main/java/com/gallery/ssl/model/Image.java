package com.gallery.ssl.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Image {

    private Integer id;
    private String name;
    private String description;
    private LocalDate createdDate;
    private Byte[] data;
    private Gallery gallery;

    /**
     * The Entity class for the image
     *
     * @author ola
     * @since 14/11/2020.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Lob
    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    @ManyToOne
    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
