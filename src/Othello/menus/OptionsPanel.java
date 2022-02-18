package Othello.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class OptionsPanel extends JPanel implements ActionListener, MouseListener {

    private StatesObservable obsrvble;
    private static final Color color = new Color(0, 78, 56);

    //behöver skicka med so så panel kan säga till att den ändrar just "den instansen av observable"
    public OptionsPanel(StatesObservable so) {
        obsrvble = so;
        setBackground(color);

        JPanel optionsPanel = new JPanel(new BorderLayout());
        optionsPanel.setBackground(color);

        optionsPanel.add(topPanel(), BorderLayout.CENTER);
        optionsPanel.add(bottomPanel(), BorderLayout.SOUTH);

    }

        private JPanel topPanel(){
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(color);

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setBackground(color);

            JButton toggleSound = new JButton("Toggle sound");
            toggleSound.setBackground(Color.black);
            toggleSound.setForeground(Color.white);
            toggleSound.addActionListener(this);

            buttonsPanel.add(toggleSound);
            topPanel.add(buttonsPanel, BorderLayout.NORTH);

            return topPanel;

        }

        private JPanel bottomPanel(){
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(color);

            JLabel back = new JLabel("Back to menu");
            back.setForeground(Color.white);
            back.addMouseListener(this);
            bottomPanel.add(back, BorderLayout.LINE_END);

            return bottomPanel;

        }



        @Override
        public void mouseClicked(MouseEvent e) {
        //trycket på texten informerar StatesObservable att ändra sig
        obsrvble.setValue(States.START);
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //toggle sound
    }
}



