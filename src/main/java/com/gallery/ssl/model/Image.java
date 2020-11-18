package com.gallery.ssl.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The Entity class for the image
 *
 * @author ola
 * @since 14/11/2020.
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private LocalDate createdDate;
    private Byte[] data;
    @ManyToOne
    @JoinColumn(name = "gallery_id", referencedColumnName="id")
    private Gallery gallery;

    public Image(){ }
    public Image(String name, String description, LocalDate createdDate, Byte[] data, Gallery gallery){
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.data = data;
        this.gallery = gallery;
    }

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


    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", data=" + Arrays.toString(data) +
                ", gallery=" + gallery +
                '}';
    }
}
