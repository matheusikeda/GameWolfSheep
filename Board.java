/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.ia.gamewolfsheep;

import java.util.ArrayList;

/**
 *
 * @author Matheus Ikeda
 */
public class Board {
    private static final int row = 8;
    private static final int column = 8;
    
    private String[][] map = new String[row][column];
    private Player player;
    private int value = 0;
    
    private Board father;
    private ArrayList<Board> child = new ArrayList<Board>();
    
    public void inicialize(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = "'";
            }
        }
        map[0][3] = "L";
        map[7][0] = "O";
        map[7][2] = "O";
        map[7][4] = "O";
        map[7][6] = "O";
    }
    
    public String[][] getMap() {
        return map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public String getXY(int row, int column) {
        return map[row][column];
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(j<7)
                    s+=map[i][j]+"    ";
                else if(j==7)
                    s+=map[i][j]+"\n\n";
                else s+="";
            }
        }
        return s+"\n";
    }

    
    
    
    
}
