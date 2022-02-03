
        package Othello;
        import javax.swing.*;
        import java.awt.*;


//något som får upp en meny. hur allt kopplas ihop idk än
public class Menu extends JPanel {
    //ska den va i konstruktor?
    private static final Color color = new Color(0, 78, 56);

    public Menu() {
        JFrame f = new JFrame("Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocation(300, 300);

        JPanel p = new JPanel();
        p.setBackground(color);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 5));

        JButton b0 = new JButton("Load New Game");
        b0.setBackground(Color.black);
        b0.setForeground(Color.white);
        buttonPanel.add(b0);

        JButton b1 = new JButton("Load Game");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        buttonPanel.add(b1);

        JButton b2 = new JButton("Options");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        buttonPanel.add(b2);

        JButton b3 = new JButton("Rules");
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        buttonPanel.add(b3);


        p.add(buttonPanel, BorderLayout.CENTER);
        f.add(p);
        f.setVisible(true);
    }


    //i labb5 hade vi main i Controll-delen men
    public static void main(String[] args) {
        Menu m = new Menu();

    }

}
