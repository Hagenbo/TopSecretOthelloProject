package Othello.model;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {

    //TODO add this method to Othello.Model

    public void SaveFile(OthelloModel model, String filename) {
            try {
                //fileName should be the name of model-object we want to save
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                //model is the model object we want to save
                oos.writeObject(model);
                oos.close();
                //promt sayin file saved?


            } catch (IOException ioException) {
                //should come up as prompt
                System.out.println("Error saving");
                ioException.printStackTrace(); //for debugging


            }
        }

}
