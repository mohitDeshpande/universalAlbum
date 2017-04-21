package com.humber.java.presenter;

import com.humber.java.model.Album;
import com.humber.java.view.AlbumViewPanel;

import com.humber.java.dao.AlbumDAO;
import com.humber.java.dao.ImageDAO;

import com.humber.java.model.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 2017-02-15.
 */
public class Gui extends JFrame {
    
    public static int WINDOW_HEIGHT = 500;
    public static int WINDOW_WIDTH = 500;
    
    private AlbumDAO albumDao = new AlbumDAO();
    private ImageDAO imageDao = new ImageDAO();

    JMenuBar menuBar;

    JMenu fileMenu;
    JMenuItem newFileMenuItem;
    JMenuItem openFileMenuItem;
    JMenuItem exitFileMenuItem;

    JMenu viewMenu;

    List<Album> albumList;
    int currentAlbumIndex;

    AlbumViewPanel albumViewPanel;

    public Gui() {
        super("Final Project. Universal image album ");
       
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumList = (ArrayList<Album>) this.albumDao.getAllAlbums();
        currentAlbumIndex = 0;
        setAlbumViewPanel(new AlbumViewPanel(albumList.get(currentAlbumIndex)));

        createMenu();
       

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    public void createMenu() {
        menuBar = new JMenuBar();

        // build the file menu;
        fileMenu = new JMenu("File");

        newFileMenuItem = new JMenuItem("New Album");
        newFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Album newAlbum = showNewAlbumDialog();
                if(newAlbum != null) {
                    albumList.add(newAlbum);
                    updateViewMenu();
                    changeAlbumViewPanel(newAlbum);
                }
            }
        });
        fileMenu.add(newFileMenuItem);

        openFileMenuItem = new JMenuItem("Open a Picture");
        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOpenImageDialog();
            }
        });
        fileMenu.add(openFileMenuItem);

        fileMenu.addSeparator();

        exitFileMenuItem = new JMenuItem("Exit");
        exitFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);


        // build the view menu
        viewMenu = new JMenu("View");
        updateViewMenu();
        menuBar.add(viewMenu);

        // set the menu onto the frame
        this.setJMenuBar(menuBar);
    }

    /**
     * Removes the AlbumViewPanel from the JFrame and sets the new AlbumViewPanel
     * @param albumViewPanel The new AlbumViewPanel to be set in the JFrame
     */
    public void setAlbumViewPanel(AlbumViewPanel albumViewPanel) {
        this.setVisible(false);
        this.albumViewPanel = albumViewPanel;
        albumViewPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumViewPanel.setVisible(true);
        this.add(this.albumViewPanel);
        this.setVisible(true);
    }

    /**
     * Used to Update the view menu with new albums data
     * To be called when a new album is added to the album list.
     */
    public void updateViewMenu() {
        viewMenu.removeAll();

        for (final Album album: albumList) {
            JMenuItem menuItem = new JMenuItem(album.getName());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeAlbumViewPanel(album);
                }
            });
            viewMenu.add(menuItem);
        }
    }

    /**
     * Create and show a New Album dialog. If correct values are entered then an Album object is returned otherwise null
     * @return Created Album object or null
     */
    public Album showNewAlbumDialog() {
        Album album = null;
        // Construct Dialog components
        JTextField nameTextField = new JTextField();
        JTextField createdTextField = new JTextField();
        JTextField editedTextField = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Album Name : "),
                nameTextField,
                new JLabel("Created On : "),
                createdTextField,
                new JLabel("Edited By  : "),
                editedTextField
        };

        // show dialog and get result and create album if result is ok
        int result = JOptionPane.showConfirmDialog(this,inputs,"Create New Album",JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                album = new Album(nameTextField.getText(), df.parse(createdTextField.getText()), editedTextField.getText());
                this.albumDao.addAlbum(album);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return album;
    }

    public void showOpenImageDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(Gui.this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsoluteFile());

            // check if file is an image
            try {
                if(ImageIO.read(selectedFile) == null) {
                    System.out.println("File is not an image");
                } else {
                    System.out.println("File is an image");
                    Album currentAlbum = albumList.get(currentAlbumIndex);
                    Picture picture = new Picture(selectedFile.getAbsolutePath(), currentAlbum);
                    this.imageDao.addImage(picture);
                    currentAlbum.addImage(picture);
                    albumViewPanel.setAndRefreshPanelData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Changes the current {@link AlbumViewPanel} with new {@link AlbumViewPanel} of supplied {@link Album}
     * @param album The new {@link Album} to be set
     */
    public void changeAlbumViewPanel(Album album) {
        remove(albumViewPanel);
        setAlbumViewPanel(new AlbumViewPanel(album));
        currentAlbumIndex = albumList.indexOf(album);
        System.out.println("Current Album Index : " + currentAlbumIndex);
    }

}
