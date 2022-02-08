package othelloController;
import Othello.model.*;


import javax.swing.*;
import java.awt.*;

public class othelloView extends JPanel {


    public othelloView() {
        JFrame gameFrame = new JFrame("Othello ");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 500);
        gameFrame.setLocation(800, 300);

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.white);
        gamePanel.setLayout(new GridLayout(8,8, 3, 3));
        gameFrame.add(gamePanel);


        gameFrame.setVisible(true);


    }
    public void setBoard(){

    }
    othelloModel othellomodel = new othelloModel("lovisa", "viktoria");




}
