package Othello.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
   // private String fileName;
    private Game loadedGame;

    public Load(/*String fileName*/){
        //this.loadedGame = load(fileName);
        //load(fileName);
    }

    public Game load(String filename) throws IOException, ClassNotFoundException {

            //FileInputStream input = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            Game stored = (Game) (objectInputStream.readObject());

            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;


    }

}
