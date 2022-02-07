package Othello;

import Othello.model.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Othello extends JFrame implements ActionListener {

    //skulle behöva en boolean sound, så man kan stänga av/sätta på ljud
    // also private static final Color color = new Color(0, 78, 56);


    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    MyButton[][] buttons = new MyButton[8][8];
    boolean player1_turn; //Om true är det Player1 tur, vid false är det Player2 tur

    Othello() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 245, 0));
        textfield.setFont(new Font("Ink free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Otello");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 700, 65);

        button_panel.setLayout(new GridLayout(8, 8));
        button_panel.setBackground(new Color(150, 150, 150));


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new MyButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setBackground(Color.GREEN);
                buttons[i][j].setOpaque(true);
            }
        }
        buttons[3][3].setDisc(PieceColor.BLACK);
        buttons[3][3].setText("SVART");
        buttons[3][4].setDisc(PieceColor.WHITE);
        buttons[3][4].setText("VIT");
        buttons[4][3].setDisc(PieceColor.WHITE);
        buttons[4][3].setText("VIT");
        buttons[4][4].setDisc(PieceColor.BLACK);
        buttons[4][4].setText("SVART");


        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn(); //Slumpar om vem som får börja


    }

    //Kan lägga in sleep i denna för att få Otello texten att synas innan spelet börjar.
    private void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("Vits tur");
        } else {
            player1_turn = false;
            textfield.setText("Svarts tur");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (player1_turn) {
                        if (buttons[i][j].getText() == "") {
                            //buttons[i][j].setForeground(new Color(255, 255, 255));
                            buttons[i][j].setText("VIT");
                            player1_turn = false; //Efter knappen är tryckt blir turnen false och därmed blir det Player 2 tur
                            textfield.setText("Svarts tur");
                            //checkWinner();
                        }
                    } else {
                        if (buttons[i][j].getText() == "") {
                            //buttons[i][j].setForeground(new Color(0, 0, 0));
                            buttons[i][j].setText("SVART");
                            player1_turn = true;
                            textfield.setText("Vits tur");
                            //checkWinner();
                        }

                    }

                }


                //public void check(){}

                //public void whiteWins(int a, int b, int c){

            }

            // public void blackWins(int a, int b, int c){}
        }
    }

    // en main så man kan testa

    public static void main (String [] args){
        Othello o = new Othello();
    }
}

