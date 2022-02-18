package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    private StatesObservable obsrvble;
    private static final Color color = new Color(0, 78, 56);

    public StartPanel(StatesObservable so){
        obsrvble = so;
        setBackground(color);
        add(this.Buttons(), BorderLayout.CENTER);


    }
    public JPanel setUpButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5));
        return buttonPanel;
    }

    public JPanel Buttons(){
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
                //game.setModel(new othelloModel("player1","player2"));
                obsrvble.setValue(States.PLAY);
                /*setPanel(game);
                createMenuBar(this);
                setSize(600, 600);
                game.revalidate();
                game.flipButtons();*/
                break;

            case "Load Game":
                //TODO load game somehow, but first the "conncection panel"
                /*String filename = JOptionPane.showInputDialog("Give a file name:");
                game.setModel(load(filename));
                setPanel(game);
                createMenuBar(this);
                setSize(600, 600);
                game.revalidate();
                game.flipButtons();*/
                break;

            case "Options":
                //setPanel(optionsPanel());
                obsrvble.setValue(States.OPTIONS);
                break;

            case "Rules":
                //setPanel(rulesPanel());
                obsrvble.setValue(States.RULES);
                break;

            case "Exit":
                System.exit(0);
                break;

           /* case "Toggle sound":
                break;*/

        }
    }

}


