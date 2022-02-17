package Othello.menus;

import Othello.othelloController.OthelloView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class OptionsPanel extends JPanel implements ActionListener, MouseListener {

    private static final Color color = new Color(0, 78, 56);
    private StartMenu startmenu;
    private OthelloView view;

    //ska jag ha private startmenu och view, eller hur når jag setPanel och toggleSound annars? Skicka med ngt i konstruktorn?

    public OptionsPanel() {

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
        //kan jag göra såhär?
            startmenu.setPanel(startmenu.menuPanel());
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
        view.toggleSound();
    }
}



