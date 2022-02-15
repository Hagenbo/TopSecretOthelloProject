package Othello;

import Othello.model.PieceColor;

import javax.swing.*;

public class MyButton extends JButton {

    private PieceColor disc;
    private int row;
    private int col;
    private ImageIcon icon;

    public MyButton(){
        super();
        disc = PieceColor.EMPTY;
    }

    public MyButton(ImageIcon icon, int i, int j/*, PieceColor disc*/){
        super();
        this.row = i;
        this.col = j;
        this.icon = icon;
        //this.disc =disc;
    }

    public PieceColor getDisc() {
        return this.disc;
    }

    public ImageIcon getIcon(){
        return this.icon;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setDisc(PieceColor disc) {
        this.disc = disc;
    }
}
