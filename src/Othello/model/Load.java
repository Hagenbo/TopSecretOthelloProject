package Othello.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {

    public Load(){}

    public Game load(String filename) throws IOException, ClassNotFoundException {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            Game stored = (Game) (objectInputStream.readObject());

            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;
    }

}
