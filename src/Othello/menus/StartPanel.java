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
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("/othelloLogo.png")));
        add(imgLabel, BorderLayout.NORTH);
        add(this.buttons(), BorderLayout.CENTER);
    }

    private JPanel setUpButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 5));
        return buttonPanel;
    }

    private JPanel buttons(){
        JPanel buttonPanel = this.setUpButtonPanel();

        JButton b0 = new JButton("SinglePlayer");
        b0.setBackground(Color.black);
        b0.setForeground(Color.white);
        b0.addActionListener(this);
        buttonPanel.add(b0);

        JButton bm = new JButton("MultiPlayer");
        bm.setBackground(Color.black);
        bm.setForeground(Color.white);
        bm.addActionListener(this);
        buttonPanel.add(bm);

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
            case "SinglePlayer":
                observable.setValue(States.SINGLEPLAYER);
                break;

            case "MultiPlayer":
                observable.setValue(States.MULTIPLAYER);
                break;

            case "Load Game":
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


