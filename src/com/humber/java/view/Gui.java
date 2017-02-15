package com.humber.java.view;

import com.humber.java.model.Album;

import javax.swing.*;

/**
 * Created by mohit on 2017-02-15.
 */
public class Gui  extends JFrame {

    public static int WINDOW_HEIGHT = 500;
    public static int WINDOW_WIDTH = 500;

    JMenuBar menuBar;

    JMenu fileMenu;
    JMenuItem newFileMenuItem;
    JMenuItem openFileMenuItem;
    JMenuItem exitFileMenuItem;

    JMenu viewMenu;

    AlbumViewPanel albumViewPanel;


    public void setAlbumViewPanel(AlbumViewPanel albumViewPanel) {
        this.setVisible(false);
        this.albumViewPanel = albumViewPanel;
        albumViewPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumViewPanel.setVisible(true);
        this.add(albumViewPanel);
        this.setVisible(true);

    }

    public Gui() {
        super("Assignment 1");
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        createMenu();


//        Album album = new Album();
//        album.setName("Test");
//        album.setCreatedOn("Summer ");
//        album.setEditedBy("Mohit");
//        album.addImage("/Users/mohit/Developer/Humber/Semester 2/Java/Assignment1/src/com/humber/java/assets/NatGeo01.jpg");
//        this.add(new AlbumViewPanel(album));

        this.setVisible(true);
    }


    public void createMenu() {
        menuBar = new JMenuBar();


        // build the file menu;
        fileMenu = new JMenu("File");

        newFileMenuItem = new JMenuItem("New Album");
        fileMenu.add(newFileMenuItem);

        openFileMenuItem = new JMenuItem("Open a Picture");
        fileMenu.add(openFileMenuItem);

        fileMenu.addSeparator();

        exitFileMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);


        // build the view menu
        viewMenu = new JMenu("View");
        menuBar.add(viewMenu);

        // set the menu onto the frame
        this.setJMenuBar(menuBar);
    }


}
