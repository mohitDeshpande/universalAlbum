package com.humber.java.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 2017-02-14.
 */
public class Album {

    private String name;
    private String createdOn;
    private String editedBy;

    private List<ImageIcon> images;

    public Album() {
    }

    public Album(String name, String createdOn, String editedBy) {
        this.name = name;
        this.createdOn = createdOn;
        this.editedBy = editedBy;
        images = new ArrayList<ImageIcon>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public List<ImageIcon> getImages() {
        return images;
    }

    /**
     * Get this count of image icons in this album
     * @return
     */
    public int getPhotoCount() {
        return this.images.size();
    }

    public void setImages(List<ImageIcon> images) {
        this.images = images;
    }
}
