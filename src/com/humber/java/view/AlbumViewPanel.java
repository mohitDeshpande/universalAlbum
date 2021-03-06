package com.humber.java.view;

import com.humber.java.model.Album;
import com.humber.java.model.Picture;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohit on 2017-02-15.
 */
public class AlbumViewPanel extends JPanel {

    private Album album;

    private JPanel metaDataPanel;
    private JLabel albumNameLabel;
    private JLabel createdOnLabel;
    private JLabel editedByLabel;

    private JPanel photoPanel;
    private JScrollPane scrollPane;

    public AlbumViewPanel(Album album) {
        this.album = album;


        // Create and populate the album meta data in the top panel
        metaDataPanel = new JPanel();
        metaDataPanel.setLayout(new GridLayout(1,3));
        albumNameLabel = new JLabel();
        createdOnLabel = new JLabel();
        editedByLabel = new JLabel();
        photoPanel = new JPanel();

        photoPanel.setLayout(new GridLayout(this.album.getPhotoCount(), 1));
        metaDataPanel.add(albumNameLabel);
        metaDataPanel.add(createdOnLabel);
        metaDataPanel.add(editedByLabel);

        setAndRefreshPanelData();

        metaDataPanel.setVisible(true);
        photoPanel.setVisible(true);
        scrollPane = new JScrollPane(photoPanel);


        this.setLayout(new BorderLayout());
        this.add(metaDataPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
        setAndRefreshPanelData();
    }

    /**
     * Adds an image to the album in this AlbumViewPanel
     * @param imagePath The image to be added to the album
     */
    public void addImageToAlbum(String imagePath) {
        this.album.addImage(imagePath);
        setAndRefreshPanelData();
    }

    /**
     * Removes Components from the album panel and adds new components according to the album
     */
    public void setAndRefreshPanelData() {
        this.setVisible(false);

        albumNameLabel.setText("Album name : " + album.getName());
        createdOnLabel.setText("Created On : " + album.getCreatedOn());
        editedByLabel.setText("Edited By : " + album.getEditedBy());

        // add each photo from the album into the photopanel
        photoPanel.removeAll();
        photoPanel.setLayout(new GridLayout(album.getPhotoCount(),1));
        for (Picture picture: album.getImages()) {
            JLabel imageLabel = new JLabel();
            imageLabel.setSize(400,400);
            imageLabel.setIcon(new ImageIcon(picture.getFile()));
            photoPanel.add(imageLabel);
        }
        this.setVisible(true);
    }

}
