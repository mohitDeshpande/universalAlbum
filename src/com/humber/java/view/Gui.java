package com.humber.java.view;

import com.humber.java.model.Album;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    List<Album> albumList;
    Album currentAlbum;

    AlbumViewPanel albumViewPanel;




    public Gui() {
        super("Assignment 1");
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumList = new ArrayList<Album>();

        createDummyAlbum();

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
        newFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAlbumFrame createAlbumFrame = new CreateAlbumFrame();
                albumList.add(createAlbumFrame.getAlbum());
                currentAlbum = createAlbumFrame.getAlbum();
                viewMenu.removeAll();

            }
        });
        fileMenu.add(newFileMenuItem);

        openFileMenuItem = new JMenuItem("Open a Picture");
        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Gui.this);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.print(selectedFile.getAbsoluteFile());
                }
            }
        });
        fileMenu.add(openFileMenuItem);

        fileMenu.addSeparator();

        exitFileMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);


        // build the view menu
        viewMenu = new JMenu("View");
        updateViewMenu();
        menuBar.add(viewMenu);

        // set the menu onto the frame
        this.setJMenuBar(menuBar);
    }

    public void setAlbumViewPanel(AlbumViewPanel albumViewPanel) {
        this.setVisible(false);
        //createMenu();
        this.albumViewPanel = albumViewPanel;
        this.currentAlbum = albumViewPanel.getAlbum();
        albumViewPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumViewPanel.setVisible(true);
        this.add(this.albumViewPanel);
        this.setVisible(true);

    }

    public void createDummyAlbum() {
        Album album1 = new Album();
        album1.setName("Assignment 1");
        album1.setCreatedOn("Winter");
        album1.setEditedBy("Mohit");
        album1.addImage("src/com/humber/java/assets/NatGeo01.jpg");
        albumList.add(album1);
        this.currentAlbum = album1;
        setAlbumViewPanel(new AlbumViewPanel(currentAlbum));

        Album album2 = new Album();
        album2.setName("Dummy");
        album2.setCreatedOn("Winter");
        album2.setEditedBy("Mohit D");
        album2.addImage("src/com/humber/java/assets/NatGeo06.jpg");
        albumList.add(album2);
    }

    public void updateViewMenu() {

        for (Album album: albumList) {
            JMenuItem menuItem = new JMenuItem(album.getName());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(albumViewPanel);
                    setAlbumViewPanel(new AlbumViewPanel(album));
                }
            });
            viewMenu.add(menuItem);
        }
    }

}
