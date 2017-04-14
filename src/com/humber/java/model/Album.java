package com.humber.java.model;

import com.humber.java.presenter.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
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
