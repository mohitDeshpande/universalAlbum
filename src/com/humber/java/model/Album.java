package com.humber.java.model;

import com.humber.java.view.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        images = new ArrayList<ImageIcon>();
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

    /**
     * Adds an image to the album image list and also scales it according to the Main window width
     * @param path The path of the image to be opened
     */
    public void addImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Image scaledImage = image.getScaledInstance(Gui.WINDOW_WIDTH,-1, Image.SCALE_SMOOTH);
            images.add(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addImage(ImageIcon imageIcon) {
        images.add(imageIcon);
    }
}
