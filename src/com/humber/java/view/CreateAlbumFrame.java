package com.humber.java.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohit on 2017-02-15.
 */
public class CreateAlbumFrame extends JFrame {

    JLabel nameLabel;
    JLabel createdLabel;
    JLabel editedLabel;

    JTextField nameTextField;
    JTextField createdTextField;
    JTextField editedTextField;

    JButton createButton;

    public CreateAlbumFrame() {
        super("Create Album");

        nameLabel = new JLabel("Album Name : ");
        createdLabel = new JLabel("Created On  : ");
        editedLabel = new JLabel("Edited By : ");

        nameTextField = new JTextField();
        createdTextField = new JTextField();
        editedTextField = new JTextField();

        createButton = new JButton("Create");


        this.setSize(500,300);
        this.setLayout(new GridLayout(4,2));
        add(nameLabel);
        add(nameTextField);
        add(createdLabel);
        add(createdTextField);
        add(editedLabel);
        add(editedTextField);
        add(new JPanel());
        add(createButton);

        this.setVisible(true);
    }
}
