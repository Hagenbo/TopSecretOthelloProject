package Othello;

import javax.swing.*;
import java.awt.*;

public class RulesTxt extends JPanel {
    private static final Color color = new Color(0, 78, 56);

    public RulesTxt() {
        JFrame f = new JFrame("Rules");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocation(400, 300);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(color);


        JLabel label = new JLabel("Back to menu");
        label.setBackground(color);
        label.setForeground(Color.white);

        bottomPanel.add(label, BorderLayout.LINE_END);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(color);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
        f.add(mainPanel);
        f.setVisible(true);
    }
    public String getText(){
        String s = "Regler fbjngjefbvi";
        return s;
    }

    public static void main (String [] args){
        RulesTxt r = new RulesTxt();
    }
}
