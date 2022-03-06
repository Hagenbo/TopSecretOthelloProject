package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * lägg till javadoc
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */

public class WaitingRoom extends JPanel /*implements MouseListener*/ {

    private StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);
    private boolean boolStart=false;
    private JButton startButton;

    public WaitingRoom(StatesObservable so) {
        observable = so;
        setBackground(color);
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("/othelloLogo.png")));

        add(imgLabel, BorderLayout.NORTH);

        startButton = new JButton("Press when ready");
        startButton.addActionListener(e -> {
            observable.setValue(States.MULTIPLAYER);
            boolStart = true;
        });
        add(startButton, BorderLayout.CENTER);

        //JPanel middlePanel = new JPanel(new BorderLayout());
        //middlePanel.setBackground(Color.ORANGE);

        //START.setForeground(Color.white);
        //START.addMouseListener(this);
        //middlePanel.add(START, BorderLayout.CENTER);


        /*JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(color);
        JTextArea info = new JTextArea("WAITING FOR OPPONENT", 20, 20);
        info.setBackground(Color.cyan);
        info.setForeground(Color.white);
        topPanel.add(info, BorderLayout.NORTH);

        setBackground(color);
        setLayout(new BorderLayout());
        //add(middlePanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.CENTER);
       /* observable.setValue(States.MULTIPLAYER);
        boolStart = true;*/


    }

    public boolean getStart(){
        return this.boolStart;
    }


  /*  @Override
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
    }*/
}
