
package Othello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class startMenu extends JFrame implements ActionListener, MouseListener {

        private static final Color color = new Color(0, 78, 56);

        public startMenu() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocation(300, 300);
            setPanel(menuPanel());
            setVisible(true);
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
                    System.out.println("New Game");
                    break;

                case "Load Game":
                    System.out.println("Load game");
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
                    System.out.println("Toggle sound");
                    break;

            }
        }


        //ska va i Controll ??
        public static void main(String[] args) {
            startMenu m = new startMenu();

        }

    }



