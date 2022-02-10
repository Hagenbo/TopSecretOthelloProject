package Othello;

import Othello.model.PieceColor;

import javax.swing.*;

public class MyButton extends JButton {

    private PieceColor disc;
    private int row;
    private int col;

    public MyButton(){
        super();
        disc = PieceColor.EMPTY;
    }

    public MyButton(String text, int i, int j/*, PieceColor disc*/){
        super(text);
        this.row = i;
        this.col = j;
        //this.disc =disc;
    }

    public PieceColor getDisc() {
        return disc;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setDisc(PieceColor disc) {
        this.disc = disc;
    }
}
