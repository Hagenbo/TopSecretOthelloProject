package Othello;

import Othello.model.PieceColor;

import javax.swing.*;

public class MyButton extends JButton {

    PieceColor disc;

    public MyButton(){
        super();
        disc = PieceColor.EMPTY;
    }

    public MyButton(String text, PieceColor disc){
        super(text);
        this.disc =disc;
    }

    public PieceColor getDisc() {
        return disc;
    }

    public void setDisc(PieceColor disc) {
        this.disc = disc;
    }
}
