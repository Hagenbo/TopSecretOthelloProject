
package Othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class startMenu extends JFrame implements ActionListener {
        private enum State{
            NEWGAME, LOADGAME, OPTIONS, RULES;
        }

        private static final Color color = new Color(0, 78, 56);

        public startMenu() {
            //JFrame start = new JFrame("Start");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocation(300, 300);
            setContentPane(menuPanel());
            setVisible(true);
        }

        public void setPan(JPanel p){
            setContentPane(p);
            validate();
        }

        //start panelen
        public JPanel menuPanel(){
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
            JPanel rulesPanel = new JPanel();
            rulesPanel.setBackground(color);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(color);
            JButton back = new JButton ("Back to menu");
            back.setForeground(Color.white);
            back.addActionListener(this);
            bottomPanel.add(back, BorderLayout.LINE_END);

            rulesPanel.add(bottomPanel, BorderLayout.PAGE_END);
            return rulesPanel;

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
                    System.out.println("New Game");
                    break;

                case "Load Game":
                    System.out.println("Load game");
                    break;

                case "Options":
                    System.out.println("Options");
                    break;

                case "Rules":
                    setPan(rulesPanel());
                    break;

                case "Exit":
                    System.exit(0);
                    break;

                case "Back to menu":
                    setPan(menuPanel());
                    break;

            }
        }


        //ska va i Controll ??
        public static void main(String[] args) {
            startMenu m = new startMenu();

        }

    }



