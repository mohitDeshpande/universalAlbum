package com.humber.java.presenter;

import com.humber.java.model.Album;
import com.humber.java.view.AlbumViewPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    int currentAlbumIndex;

    AlbumViewPanel albumViewPanel;


    // db variables
    Connection connection = null;
    String url = "jdbc:mysql://vaulty.cloud:3306/java_album";
    String userid = "java_album";
    String password = "Java_@lbum";
    

    public Gui() {
        super("Assignment 1");
        
        // try connecting to the db
        try {
            
            System.out.println("Establishing connection to the database ...");
            this.connection = DriverManager.getConnection(this.url, userid, password);    
            
            System.out.println("Connection Established ...");
        } catch (SQLException ex) {
            System.err.println("An error has occured while trying to connect to the database " + ex.getMessage());
            
        }
        
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        albumList = new ArrayList<Album>();

        createDummyAlbum();

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
     * Creates 2 dummy albums for testing purpose
     */
    public void createDummyAlbum() {
        Album album1 = new Album();
        album1.setName("Assignment 1");
        album1.setCreatedOn("Winter");
        album1.setEditedBy("Mohit");
        album1.addImage("/Users/mohit/Desktop/java/Assignment1/src/com/humber/java/assets/NatGeo01.jpg");
        albumList.add(album1);
        this.currentAlbumIndex = 0;
        setAlbumViewPanel(new AlbumViewPanel(albumList.get(currentAlbumIndex)));

        Album album2 = new Album();
        album2.setName("Dummy");
        album2.setCreatedOn("Winter");
        album2.setEditedBy("Mohit D");
        album2.addImage("/Users/mohit/Desktop/java/Assignment1/src/com/humber/java/assets/NatGeo06.jpg");
        albumList.add(album2);
        
        // get third album from DB
        try {
        
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select name,description, flag from country");
            while(rs.next()) {
                String name = rs.getString("name");
                ImageIcon flag = new ImageIcon(rs.getBlob("flag").getBytes(1, (int)rs.getBlob("flag").length()));
                String description = rs.getString("description");
            
                Album album3 = new Album();
                album3.setName(name);
                album3.setCreatedOn("example");
                album3.setEditedBy(description);
                album3.addImage(flag);
                albumList.add(album3);
            }
        
        } catch(SQLException ex) {
            System.out.println("Error occured: "+ex.getMessage());
            ex.printStackTrace();
        }
        
        
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
            album = new Album(nameTextField.getText(), createdTextField.getText(), editedTextField.getText());
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
                    albumList.get(currentAlbumIndex).addImage(selectedFile.getAbsolutePath());
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
