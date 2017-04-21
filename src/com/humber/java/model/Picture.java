/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humber.java.model;

import com.humber.java.presenter.Gui;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author DS
 */
public class Picture {
     private byte[] file;
     private Album album;
     private int id;
     
     public Picture() {
         
     }
     
     public Picture(String path, Album album) {
        try {
            this.album = album;
            File image = new File(path);

            this.file = new byte[(int) image.length()];
            try {
                  FileInputStream fileInputStream = new FileInputStream(image);
                  fileInputStream.read(this.file);
             } catch (FileNotFoundException e) {
                         System.out.println("File Not Found.");
                         e.printStackTrace();
             }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
    
    public Album getAlbum() {
        return this.album;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public void setFile(String path) {
        try {          
            File image = new File(path);

            this.file = new byte[(int) image.length()];
            try {
                  FileInputStream fileInputStream = new FileInputStream(image);
                  fileInputStream.read(this.file);
             } catch (FileNotFoundException e) {
                  System.out.println("File Not Found.");
                  e.printStackTrace();
             }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setFile(byte [] file) {
        this.file = file;
    }
    
    public byte[] getFile() {
        return this.file;
    }

}
