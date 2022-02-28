package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RulesPanel extends JPanel implements MouseListener {
    private StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);

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

    private String getRules() {
        String str = " \n" +
                "Othello is a strategy board game played between 2 players. \n" +
                "One player plays black and the other white.\n" +
                "\n" +
                "Each player gets 32 discs and black always starts the game. Then the game alternates \n" +
                "between white and black until: \n \n" +
                "one player can not make a valid move to outflank the opponent \n" +
                "both players have no valid moves.\n \n" +
                "When a player has no valid moves, he pass his turn and the opponent continues. \n" +
                "When both players can not make a valid move the game ends, and the player with \n" +
                "the most discs in their colour wins.\n \n" +
                "Good luck!";
        return str;
    }

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
