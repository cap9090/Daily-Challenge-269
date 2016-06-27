/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirrorencryption;

/**
 *
 * @author CAP
 */
public class Letter {
    
    private char letter;
    private int index;
    private String partOfGrid;

    public Letter(char letter, int index, String partOfGrid) {
        this.letter = letter;
        this.index = index;
        this.partOfGrid = partOfGrid;
    }

    public char getLetter() {
        return letter;
    }

    public int getIndex() {
        return index;
    }

    public String getPartOfGrid() {
        return partOfGrid;
    }
    
    
}
