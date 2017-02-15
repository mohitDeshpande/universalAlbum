package com.humber.java;

import com.humber.java.model.Album;
import com.humber.java.view.AlbumViewPanel;
import com.humber.java.view.Gui;

import javax.swing.*;

/**
 * Created by mohit on 2017-02-13.
 */
public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();

        Album album = new Album();
        album.setName("Test");
        album.setCreatedOn("Summer ");
        album.setEditedBy("Mohit");
        ImageIcon i = new ImageIcon("/Users/mohit/Developer/Humber/Semester 2/Java/Assignment1/src/com/humber/java/assets/NatGeo01.jpg");
        album.addImage("src/com/humber/java/assets/NatGeo01.jpg");

        AlbumViewPanel albumViewPanel = new AlbumViewPanel(album);

        gui.setAlbumViewPanel(albumViewPanel);
    }

}
