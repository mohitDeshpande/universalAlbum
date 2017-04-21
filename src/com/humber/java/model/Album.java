package com.humber.java.model;

import com.humber.java.presenter.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by DS
 */
public class Album {
    private int id;
    private String name;
    private Date createdOn;
    private String editedBy;

    private Set<Picture> images;

    public Album() {
        
    }

    public Album(String name, Date createdOn, String editedBy) {
        this.name = name;
        this.createdOn = createdOn;
        this.editedBy = editedBy;
    }
    
    public Album(String name, Date createdOn, String editedBy, Set<Picture> pictures) {
        this.name = name;
        this.createdOn = createdOn;
        this.editedBy = editedBy;
        this.images = pictures;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int albumId) {
        this.id = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public Set<Picture> getImages() {
        return images;
    }

    /**
     * Get this count of image icons in this album
     * @return
     */
    public int getPhotoCount() {
        return this.images.size();
    }

    public void setImages(Set<Picture> images) {
        this.images = images;
    }

    /**
     * Adds an image to the album image list and also scales it according to the Main window width
     * @param path The path of the image to be opened
     */
    public void addImage(String path) {
        Picture picture = new Picture(path, this);
        images.add(picture);
    }

    public void addImage(Picture image) {
        images.add(image);
    }

    @Override
    public String toString() {
        return "Album [id=" + id + ", name=" + name
                + ", createdOd=" + createdOn.toString() + ", editedBy=" + editedBy + "]";
    }     

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Album) {
            Album album = (Album)obj;
            if(album.getName().equals(this.name) && album.getCreatedOn().equals(this.createdOn) && album.getEditedBy().equals(this.editedBy)){
                return true;
            }
        }
        return false;
    }
}
