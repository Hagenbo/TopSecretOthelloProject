package Othello.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Class containing method for saving game after its basic information is stored within a SaveInfo object
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-04
 */
public class Save {
    public Save(){}

    /**
     * Saves a SaveInfo instance to a file
     * @param si - new instance of SaveInfo
     * @param filename - the file name
     */
    public void save(SaveInfo si, String filename) {
        try {
            FileOutputStream output = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(si);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println(filename + " stored.");
        } catch (IOException e) {
            System.out.println("save failed because " + e);
        }
    }
}
