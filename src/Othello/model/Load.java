package Othello.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class containing method for loading a saved game as a SaveInfo object
 * @Version 2022-03-04
 */
public class Load {

    public Load(){}

    /**
     * Loads a saved game as a SaveInfo object
     * @param filename
     * @return SaveInfo stored
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public SaveInfo load(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        SaveInfo stored = (SaveInfo) (objectInputStream.readObject());

        objectInputStream.close();
        // delete this when final clean up System.out.println("Loaded " + filename);
        return stored;


    }

}
