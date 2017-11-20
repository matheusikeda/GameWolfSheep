/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.ia.gamewolfsheep;

/**
 *
 * @author Matheus Ikeda
 */
public class Sheep {
    private int row;
    private int column;

    public Sheep(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public Sheep(Sheep w) {
        this.row = w.row;
        this.column = w.column;
    }
    
    public int getRow(){
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
}
