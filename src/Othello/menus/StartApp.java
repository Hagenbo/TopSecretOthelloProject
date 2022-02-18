
package Othello.menus;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

import Othello.model.OthelloModel;
import Othello.othelloController.*;


public class StartApp extends JFrame implements PropertyChangeListener {

        private OthelloView game;
        private JPanel panel;
        private States state;
        private StartPanel sp;
        private OptionsPanel op;
        private RulesPanel rp;
        private StatesObservable obsrvble;


        public StartApp() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocation(200, 200);

            state= States.START;
            obsrvble = new StatesObservable();
            //skickar med "samma obsrvble" till startpaneln så dom jobbar med samma lxm
            sp = new StartPanel(obsrvble);
            op = new OptionsPanel(obsrvble);
            rp = new RulesPanel(obsrvble);
            setContentPane(sp);

            //lägger till startApp som observer till observable
            obsrvble.addPropertyChangeListener(this);
            setVisible(true);

        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            //kollar om eventet är en instans av States, dvs att state ändrats
        if (evt.getNewValue() instanceof States) {
            //i så fall, tar det nya värdet (av state) typecast till State, sätter till state som nytt värde
            state = (States) evt.getNewValue();
            setPanel();
        }

    }
        public void setPanel(){
            if(state == States.START){
                this.panel = sp;
                setContentPane(sp);
                validate();
            }
            else if(state == States.OPTIONS){
                this.panel = op;
                setContentPane(op);
                validate();
            }

            else if(state == States.RULES){
                this.panel = rp;
                setContentPane(rp);
                validate();
            }

            else if(state == States.PLAY){
                game = new OthelloView(new OthelloModel("player1","player2"), obsrvble);
                game.setModel(new OthelloModel("player1","player2"));
                setContentPane(game);
                //createMenuBar(this);
                setSize(600, 600);
                game.revalidate();
                game.flipButtons();
            }
        }

        /*public JPanel menuPanel() {
            JPanel menuPanel = new JPanel();
            menuPanel.setBackground(color);
            menuPanel.add(this.Buttons(), BorderLayout.CENTER);
            return menuPanel;
        }

        public JPanel setUpButtonPanel(){
            JPanel bp = new JPanel();
            bp.setLayout(new GridLayout(5, 5));
            return bp;
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

        }*/


        /*public JPanel rulesPanel(){

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(color);
            JLabel back = new JLabel ("Back to menu");
            back.setForeground(Color.white);
            back.addMouseListener(this);
            bottomPanel.add(back, BorderLayout.LINE_END);

            JPanel topPanel = new JPanel();
            topPanel.setBackground(color);
            JTextArea rulesText = new JTextArea(getRules(), 10, 10 );
            rulesText.setBackground(color);
            rulesText.setForeground(Color.white);
            topPanel.add(rulesText);

            JPanel rulesPanel = new JPanel(new BorderLayout());
            rulesPanel.setBackground(color);

            rulesPanel.add(topPanel, BorderLayout.CENTER);
            rulesPanel.add(bottomPanel, BorderLayout.SOUTH);

            return rulesPanel;
        }

        public String getRules(){
            String str = "MAssa regler sjdfhdwsöuihwuiöewhiuöweywuiw23838bfebjs";
            return str;
        }*/


       /*public JPanel optionsPanel() {
           JPanel topPanel = new JPanel(new BorderLayout());
           topPanel.setBackground(color);

           JPanel buttonsPanel = new JPanel();
           buttonsPanel.setBackground(color);
           //should we remove toggle sound from options?
           JButton toggleSound = new JButton("Toggle sound");
           toggleSound.setBackground(Color.black);
           toggleSound.setForeground(Color.white);
           toggleSound.addActionListener(this);

           buttonsPanel.add(toggleSound);
           topPanel.add(buttonsPanel, BorderLayout.NORTH);

           JPanel bottomPanel = new JPanel(new BorderLayout());
           bottomPanel.setBackground(color);

           JLabel back = new JLabel("Back to menu");
           back.setForeground(Color.white);
           back.addMouseListener(this);
           bottomPanel.add(back, BorderLayout.LINE_END);

           JPanel optionsPanel = new JPanel(new BorderLayout());
           optionsPanel.setBackground(color);
           optionsPanel.add(topPanel, BorderLayout.CENTER);
           optionsPanel.add(bottomPanel, BorderLayout.SOUTH);

           return optionsPanel;
       }*/




       /* @Override
        public void actionPerformed(ActionEvent e){
            Object obj = e.getSource();
            if(!(obj instanceof JButton b)) {
                return;
            }

            String str = b.getText();

            switch(str){
                case "New Game":
                    //TODO start a new game, have a new JPanel where players put in their names (and IP-adresses if thats how this works)?
                    game.setModel(new othelloModel("player1","player2"));
                    setPanel(game);
                    createMenuBar(this);
                    setSize(600, 600);
                    game.revalidate();
                    game.flipButtons();
                    break;

                case "Load Game":
                    //TODO load game somehow, but first the "conncection panel"
                    String filename = JOptionPane.showInputDialog("Give a file name:");
                    game.setModel(load(filename));
                    setPanel(game);
                    createMenuBar(this);
                    setSize(600, 600);
                    game.revalidate();
                    game.flipButtons();
                    break;

                case "Options":
                    setPanel(optionsPanel());
                    break;

                case "Rules":
                    setPanel(rulesPanel());
                    break;

                case "Exit":
                    System.exit(0);
                    break;

                case "Toggle sound":
                    break;

            }
        }*/




//TODO add to model. Fix buggs
    private void save(OthelloModel model, String filename) {
        try {
            FileOutputStream output = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(model);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println(filename + " stored.");
        } catch (IOException e) {
            System.out.println("save failed because " + e);
        }
    }

    private OthelloModel load(String filename) {
        try {
            FileInputStream input = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            OthelloModel stored = (OthelloModel) (objectInputStream.readObject());
            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;
        } catch (Exception e) {
            System.out.println("load failed because " + e);
            //System.out.println("returned current game.");
            //return othelloView.getModel(); //getModel static?
            return new OthelloModel("player1","player2"); //tillfällig lösning tills getModel funkar
        }
    }

   /* private void createMenuBar(JFrame f) {
        JMenuBar menuBar = new JMenuBar();

        JMenu quit = new JMenu("Quit");
        quit.addMenuListener(this);
        menuBar.add(quit);

        //TODO ask if player is certain they want to withdraw, update panel how?
        JMenu withdraw = new JMenu("Withdraw");
        withdraw.addMenuListener(this);
        menuBar.add(withdraw);

        JMenu toggleSound = new JMenu("Toggle sound");
        toggleSound.addMenuListener(this);
        menuBar.add(toggleSound);

        JMenu saveGame = new JMenu("Save Game");
        menuBar.add(saveGame);
        saveGame.addMenuListener(this);

        f.setJMenuBar(menuBar);
    }



    @Override
    public void menuSelected(MenuEvent e) {
        Object obj = e.getSource();
        if (!(obj instanceof JMenu j)) {
            return;
        }
        String str = j.getText();

        switch (str) {
            case "Withdraw":
                setJMenuBar(null);
                setSize(500, 500);
                setPanel(menuPanel());
                break;

            case "Save Game":
                String filename = JOptionPane.showInputDialog("Enter a file name:");
                save(game.getModel(), filename);
                //saveTest.SaveFile(game.getModel(), filename);
                break;

            case "Quit":
                //do something
                break;

            case "Toggle sound":
                game.toggleSound();
                break;
        }
    }

        @Override
        public void menuDeselected(MenuEvent e) {
        }
        @Override
        public void menuCanceled(MenuEvent e) {
        }
*/




        public static void main(String[] args) {
            StartApp start = new StartApp();
        }


}



