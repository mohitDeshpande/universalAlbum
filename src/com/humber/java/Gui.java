package com.humber.java;

import javax.swing.*;

/**
 * Created by mohit on 2017-02-15.
 */
public class Gui  extends JFrame {

    JMenuBar menuBar;

    JMenu fileMenu;
    JMenuItem newFileMenuItem;
    JMenuItem openFileMenuItem;
    JMenuItem exitFileMenuItem;

    JMenu viewMenu;

    public Gui() {
        super("Assignment 1");
        this.setSize(500,500);

        createMenu();


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
