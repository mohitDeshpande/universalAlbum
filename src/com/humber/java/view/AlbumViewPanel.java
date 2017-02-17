package com.humber.java.view;

import com.humber.java.model.Album;

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

        setPanelData();

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
        setPanelData();
    }


    public void setPanelData() {
        albumNameLabel.setText("Album name : " + album.getName());
        createdOnLabel.setText("Created On : " + album.getCreatedOn());
        editedByLabel.setText("Edited By : " + album.getEditedBy());

        // add each photo from the album into the photopanel
        for (ImageIcon image: album.getImages()) {
            JLabel imageLabel = new JLabel();
            imageLabel.setSize(400,400);
            imageLabel.setIcon(image);
            photoPanel.add(imageLabel);
        }
    }
}
