package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    private  final StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);

    public StartPanel(StatesObservable so){
        observable= so;
        setBackground(color);
        add(this.buttons(), BorderLayout.CENTER);
    }

    public JPanel setUpButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5));
        return buttonPanel;
    }

    public JPanel buttons(){
        JPanel buttonPanel = this.setUpButtonPanel();

        JButton b0 = new JButton("New Game");
        b0.setBackground(Color.black);
        b0.setForeground(Color.white);
        b0.addActionListener(this);
        buttonPanel.add(b0);

        JButton b1 = new JButton("Load Game");
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        buttonPanel.add(b1);

        JButton b2 = new JButton("Options");
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        buttonPanel.add(b2);

        JButton b3 = new JButton("Rules");
        b3.setBackground(Color.black);
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        buttonPanel.add(b3);

        JButton b4 = new JButton("Exit");
        b4.setBackground(Color.black);
        b4.setForeground(Color.white);
        b4.addActionListener(this);
        buttonPanel.add(b4);

        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(!(obj instanceof JButton b)) {
            return;
        }
        String str = b.getText();

        switch(str){
            case "New Game":
                //TODO start a new game, have a new JPanel where players put in their names (and IP-adresses if thats how this works)?
                observable.setValue(States.PLAY);
                break;

            case "Load Game":
                //TODO load game somehow, but first the "conncection panel"
                observable.setValue(States.LOAD);
                break;

            case "Options":
                observable.setValue(States.OPTIONS);
                break;

            case "Rules":
                observable.setValue(States.RULES);
                break;

            case "Exit":
                System.exit(0);
                break;
        }
    }
}


