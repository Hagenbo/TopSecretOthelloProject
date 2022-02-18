package Othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {
    private enum State{
        NEWGAME, LOADGAME, OPTIONS, RULES;
    }

    private static final Color color = new Color(0, 78, 56);

    public Menu() {
        JFrame f = new JFrame("Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocation(300, 300);
        JPanel p = new JPanel();
        p.setBackground(color);
        p.add(this.Buttons(), BorderLayout.CENTER);
        f.add(p);
        f.setVisible(true);
    }

    public JPanel setUpButtonPanel(){
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(5, 5));
        return bp;
    }
    public JPanel Buttons(){
        JPanel buttonPanel = this.setUpButtonPanel();

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

        JButton b4 = new JButton("Exit");
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        buttonPanel.add(b4);

        return buttonPanel;

    }

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
                RulesTxt rt = new RulesTxt();
                break;
            case "Exit":
                //funkar nt
                System.exit(0);
                break;

        }
    }



    //ska va i Controll ??
    public static void main(String[] args) {
        Menu m = new Menu();

    }

}

