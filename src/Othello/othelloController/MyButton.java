package Othello.othelloController;

import javax.swing.*;

public class MyButton extends JButton {

    private int row;
    private int col;
    private ImageIcon icon;

    public MyButton(ImageIcon icon, int i, int j){
        super();
        this.row = i;
        this.col = j;
        this.icon = icon;
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

    public void setIcon2(ImageIcon icon) {
        this.icon = icon;
        setIcon(icon);
    }
}
