package Othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {
    //ska den va i konstruktor?
    private static final Color color = new Color(0, 78, 56);

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(!(obj instanceof JButton)){
            return;
        }
        //typecast Object obj till en JButton
        JButton b = (JButton)obj;
        String str = b.getText();

        switch(str){
            case "New Game":
                System.out.println("New Game");
                break;
            case "Load Game":
                System.out.println("Load game");
                break;
            case "Options":
                System.out.println("Options");
                break;
            case "Rules":
                System.out.println("Rules");
                break;

        }
    }

    public Menu() {
        JFrame f = new JFrame("Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocation(300, 300);

        JPanel p = new JPanel();
        p.setBackground(color);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 5));

        JButton b0 = new JButton("New Game");
        b0.setBackground(Color.black);
        b0.setForeground(Color.white);
        b0.addActionListener(this);
        buttonPanel.add(b0);

        JButton b1 = new JButton("Load Game");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        buttonPanel.add(b1);

        JButton b2 = new JButton("Options");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        buttonPanel.add(b2);

        JButton b3 = new JButton("Rules");
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        buttonPanel.add(b3);

        p.add(buttonPanel, BorderLayout.CENTER);
        f.add(p);
        f.setVisible(true);
    }


    //ska va i Controll ??
    public static void main(String[] args) {
        Menu m = new Menu();

    }

}

