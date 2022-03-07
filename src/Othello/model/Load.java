package Othello.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class containing method for loading a saved game as a SaveInfo object
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-04
 */
public class Load {

    public Load(){}

    /**
     * Loads a saved game as a SaveInfo object
     * @param filename - name of file
     * @return SaveInfo stored
     * @throws IOException - if IO problems
     * @throws ClassNotFoundException - if the file can not be found
     */
    public SaveInfo load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        SaveInfo stored = (SaveInfo) (objectInputStream.readObject());

        objectInputStream.close();
        return stored;


    }

}
