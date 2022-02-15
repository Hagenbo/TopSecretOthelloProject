package Othello.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class optionMenu extends JPanel implements ActionListener {

    private static final Color color = new Color(0, 78, 56);



    public optionMenu (){
        setBackground(color);
        add(this.optionButtons(), BorderLayout.CENTER);
    }

    public JPanel setUpOptionButtonPanel(){
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(1, 5));
        return bp;
    }

    public JPanel optionButtons(){
        JPanel optionsPanel = this.setUpOptionButtonPanel();
        JButton sound = new JButton("Turn off sound");
        sound.setBackground(Color.black);
        sound.setForeground(Color.white);
        sound.addActionListener(this);

        JLabel back = new JLabel("Back to menu");
        back.setBackground(color);
        back.setForeground(Color.white);
        back.addMouseListener(backListener);
        optionsPanel.add(back);


        add(sound);

        return optionsPanel;
    }


    MouseListener backListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //här vill jag tillbaka till startMenu. kommer inte
            //åt setPanel funktionen eller menuPanel dock
            System.out.println("Back to menu");
        }

        //måste dom va här.....?
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    };


    public void actionPerformed(ActionEvent e){
        System.out.println("toggle sound");

    }


}

