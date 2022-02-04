package Othello;

import javax.swing.*;

public class RulesTxt extends JPanel {
    public RulesTxt() {
        JFrame f = new JFrame("Rules");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocation(400, 300);
        JPanel p = new JPanel();
        JTextField l = new JTextField();
        l.setText(getText());
        f.add(p.add(l));
        f.setVisible(true);
    }

    public String getText(){
        String s = "Regler fbjngjefbvi";
        return s;
    }
}
