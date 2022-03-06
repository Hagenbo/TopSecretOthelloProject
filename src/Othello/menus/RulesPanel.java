package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that creates the JPanel RulesPanel which displays the rules. Implements MouseListener
 * for the buttons.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */

public class RulesPanel extends JPanel implements MouseListener {
    private StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);

    /**
     * Contructor that creates a JPanel. Sets up the panel with its JTextArea and JLabel.
     * Delegates ActionListeners.
     * @param so - observer for changing the state State
     */
    public RulesPanel(StatesObservable so) {
        observable = so;

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(color);
        JLabel back = new JLabel("Back to menu");
        back.setForeground(Color.white);
        back.addMouseListener(this);
        bottomPanel.add(back, BorderLayout.LINE_END);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(color);
        JTextArea rulesText = new JTextArea(getRules(), 20, 20);
        rulesText.setBackground(color);
        rulesText.setForeground(Color.white);
        topPanel.add(rulesText, BorderLayout.CENTER);

        setBackground(color);
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Method for getting the string of rules.
     * @return String str
     */
    private String getRules() {
        String str = " \n" +
                "Othello is a strategy board game played between 2 players. \n" +
                "One player plays black and the other white.\n" +
                "\n" +
                "Each player gets 32 discs and black always starts the game. Then the game alternates \n" +
                "between white and black until: \n" +
                "   - one player can not make a valid move to outflank the opponent \n" +
                "   - both players have no valid moves.\n \n" +
                "When a player has no valid moves, he pass his turn and the opponent continues. \n" +
                "When both players can not make a valid move the game ends, and the player with \n" +
                "the most discs in their colour wins.\n" +
                "Good luck!";
        return str;
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
}
