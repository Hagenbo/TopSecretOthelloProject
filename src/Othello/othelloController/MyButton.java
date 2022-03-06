package Othello.othelloController;

import javax.swing.*;
/**
 * Class for the discs used in Othello. Implemented with JButton.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */

public class MyButton extends JButton {

    private int row;
    private int col;
    private ImageIcon icon;

    /**
     * Constructor that initializes row, col, and icon variables.
     * @param icon - icon for the button
     * @param i - row
     * @param j - column
     */
    public MyButton(ImageIcon icon, int i, int j){
        super();
        this.row = i;
        this.col = j;
        this.icon = icon;
    }

    /**
     * Method getting icon
     * @return this icon
     */
    public ImageIcon getIcon(){
        return this.icon;
    }

    /**
     * Method getting row
     * @return this row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Method getting column
     * @return this column
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Method setting an image
     * @param icon - the png image we want to delegate on a disc. Calls method setIcon.
     */
    public void setIcon2(ImageIcon icon) {
        this.icon = icon;
        setIcon(icon);
    }
}
