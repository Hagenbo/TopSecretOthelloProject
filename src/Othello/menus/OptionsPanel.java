package Othello.menus;

import Othello.model.Options;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * Class that creates the JPanel OptionsPanel. Implements ActionListener, MouseListener
 * for the buttons.
 * @Version 2022-03-06
 */
public class OptionsPanel extends JPanel implements ActionListener, MouseListener {

    private final StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);
    private JLabel optionsOverview;
    private final Options sound;

    /**
     * Contructor that creates a JPanel
     * @param so - observer for changing the state State
     * @param sound - for toggling sound
     */
    public OptionsPanel(StatesObservable so, Options sound) {
        observable = so;
        this.sound = sound;
        setBackground(color);

        setLayout(new BorderLayout());

        add(topPanel(), BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);
    }

    /**
     * Method for setting up the top panel with buttons for the JPanel. Delegates ActionListeners.
     * @return JPanel topPanel
     */
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

    /**
     * Method for setting up the bottom panel with a JLabel for the JPanel. Delegates ActionListeners.
     * @return JPanel bottomPanel
     */
        private JPanel bottomPanel(){
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(color);

            JLabel back = new JLabel("Back to menu");
            back.setForeground(Color.white);
            back.addMouseListener(this);
            bottomPanel.add(back, BorderLayout.LINE_END);

            this.optionsOverview = new JLabel("Sound: " + sound.isSoundOn());
            optionsOverview.setForeground(Color.white);
            bottomPanel.add(optionsOverview, BorderLayout.LINE_START);

            return bottomPanel;

        }

    /**
     * MouseClicked event for the MouseListeners. Changes the state and notifies the observer, which
     * brings the player back to the start panel.
     * @param e - MouseEvent
     */
        @Override
        public void mouseClicked(MouseEvent e) {
        observable.setValue(States.START);
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

    /**
     * ActionPerformed for the ActionListeners. Toggles the sound.
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        sound.toggleSound();
        optionsOverview.setText("Sound: " + sound.isSoundOn());
    }
}



