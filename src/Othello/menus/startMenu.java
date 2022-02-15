
package Othello.menus;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import Othello.model.othelloModel;
import Othello.othelloController.*;

public class startMenu extends JFrame implements ActionListener, MouseListener {

        private static final Color color = new Color(0, 78, 56);
        //private othelloModel om behövs detta? för soundOn osv

        private othelloView game;
        //ov.om.gameOver();
        public startMenu() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocation(300, 300);
            setPanel(menuPanel());
            setVisible(true);
            game = new othelloView(new othelloModel("player1","player2"));
        }

        public void setPanel(JPanel p){
            setContentPane(p);
            validate();
        }

        //start panelen
        public JPanel menuPanel() {
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

        }

        //rules panelen
        public JPanel rulesPanel(){

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
        }


       public JPanel optionsPanel() {
           JPanel topPanel = new JPanel(new BorderLayout());
           topPanel.setBackground(color);

           JPanel buttonsPanel = new JPanel();
           buttonsPanel.setBackground(color);
           //should we remove toggle sound from options?
           JButton toggleSound = new JButton("Toggle sound");
           toggleSound.setBackground(Color.black);
           toggleSound.setForeground(color.white);
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
       }

            @Override
            public void mouseClicked(MouseEvent e) {
                setPanel(menuPanel());
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
        public void actionPerformed(ActionEvent e){
            Object obj = e.getSource();
            if(!(obj instanceof JButton)) {
                return;
            }
            //typecast Object obj till en JButton
            JButton b = (JButton)obj;
            String str = b.getText();

            switch(str){
                case "New Game":
                    //TODO start a new game, have a new JPanel where players put in their names (and IP-adresses if thats how this works)?
                    System.out.println("New Game");
                    setPanel(game);
                    createMenuBar(this);
                    game.revalidate();
                    game.flipButtons();
                    break;

                case "Load Game":
                    //TODO load game somehow, but first the "conncection panel"
                    String filename = JOptionPane.showInputDialog("Give a file name:");
                    game.setModel(load(filename));
                    setPanel(game);
                    createMenuBar(this);
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
        }

    private void save(othelloModel model, String filename) {
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

    private othelloModel load(String filename) {
        try {
            FileInputStream input = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            othelloModel stored = (othelloModel) (objectInputStream.readObject());
            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;
        } catch (Exception e) {
            System.out.println("load failed because " + e);
            //System.out.println("returned current game.");
            //return othelloView.getModel(); //getModel static?
            return new othelloModel("player1","player2"); //tillfällig lösning tills getModel funkar
        }
    }

    private void createMenuBar(JFrame f) {
        JMenuBar menuBar = new JMenuBar();

        JMenu quit = new JMenu("Quit");
        menuBar.add(quit);

        JMenu withdraw = new JMenu("Withdraw");
        menuBar.add(withdraw);

        JMenu toggleSound = new JMenu("Toggle sound");
        menuBar.add(toggleSound);

        JMenu saveGame = new JMenu("Save Game");
        menuBar.add(saveGame);
        saveGame.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                String filename = JOptionPane.showInputDialog("Give a file name:");
                save(game.getModel(),filename);
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        //TODO add actionListeners, instance of?
        // dont use dynamic class

        f.setJMenuBar(menuBar);

    }



        public static void main(String[] args) {
            startMenu m = new startMenu();

        }

    }



