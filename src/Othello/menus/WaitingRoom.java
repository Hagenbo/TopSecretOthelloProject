package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WaitingRoom extends JPanel implements MouseListener {

    private StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);
    private boolean boolStart=false;

    public WaitingRoom(StatesObservable so) {
        observable = so;

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBackground(Color.ORANGE);
        JLabel START = new JLabel("Press to Start");
        START.setForeground(Color.white);
        START.addMouseListener(this);
        middlePanel.add(START, BorderLayout.CENTER);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(color);
        JTextArea info = new JTextArea("WAITING FOR OPPONENT", 20, 20);
        info.setBackground(Color.cyan);
        info.setForeground(Color.white);
        topPanel.add(info, BorderLayout.NORTH);

        setBackground(color);
        setLayout(new BorderLayout());
        add(middlePanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.CENTER);
    }

    public boolean getStart(){
        return this.boolStart;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        observable.setValue(States.MULTIPLAYER);
        boolStart = true;
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
