package com.humber.java.view;

import com.humber.java.model.Album;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Album getAlbum() {
        return album;
    }

    private Album album;

    public CreateAlbumFrame() {
        super("Create Album");

        nameLabel = new JLabel("Album Name : ");
        createdLabel = new JLabel("Created On  : ");
        editedLabel = new JLabel("Edited By : ");

        nameTextField = new JTextField();
        createdTextField = new JTextField();
        editedTextField = new JTextField();

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String create = createdTextField.getText();
                String edit = editedTextField.getText();

                System.out.println(name + create + edit);

                album = new Album(name,create,edit);

                setVisible(false);
            }
        });


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
