package Othello.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load {
   // private String fileName;
    private Game loadedGame;

    public Load(/*String fileName*/){
        //this.loadedGame = load(fileName);
        //load(fileName);
    }

    public Game load(String filename) {
        try {
            //FileInputStream input = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            Game stored = (Game) (objectInputStream.readObject());

            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;


        } catch (Exception e) {
            //TODO add prompt
            System.out.println("load failed because ");
            e.printStackTrace();
            return new Game("player 1", "player 2", new Board(8) );

        }
    }

}
