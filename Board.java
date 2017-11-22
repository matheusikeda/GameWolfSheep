/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.ia.gamewolfsheep;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Ikeda
 */
public class Board implements Cloneable{
    private static final int LENGTH = 8;
    
    private String[][] map;
    private Player player;
    private Wolf wolf;
    private Sheep sheep1;
    private Sheep sheep2;
    private Sheep sheep3;
    private Sheep sheep4;
    private int value = 0;
    private ArrayList<Board> child = new ArrayList<Board>();    

    public Board(String[][] map, Player player, Wolf wolf, Sheep sheep1, Sheep sheep2, Sheep sheep3, Sheep sheep4) {
        this.map = map;
        this.player = player;
        this.wolf = wolf;
        this.sheep1 = sheep1;
        this.sheep2 = sheep2;
        this.sheep3 = sheep3;
        this.sheep4 = sheep4;
    }
    public Board(){
        
    }
    
    public void inicialize(){
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = "O";
            }
        }
        map[0][3] = "W";
        map[7][0] = "S";       
        map[7][2] = "S"; 
        map[7][4] = "S";
        map[7][6] = "S";
        
    }
    public void iniciaArray(Board b){
        this.child.add(b);
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
    public void setS(int row, int column) {
        map[row][column] = "S";
    }
    public void setW(int row, int column) {
        map[row][column] = "W";
    }
    //Limpa o campo
    public void setO(int row, int column) {
        map[row][column] = "O";
    }
    //verifica se computador perdeu (Lobo ganhou)
    public boolean lose(){
        return wolf.getRow() == 7 || (sheep1.getRow() == 0 && sheep2.getRow() == 0 && sheep3.getRow() == 0 && sheep4.getRow() == 0);
    }
    public boolean win(){
        if(wolf.getRow() == 0 && wolf.getColumn() == 7){
            if(!"O".equals(map[wolf.getRow()+1][wolf.getColumn()-1])){
                return true;
            }
        }else if(wolf.getRow() == 0 && wolf.getColumn() != 7){
            if(!"O".equals(map[wolf.getRow()+1][wolf.getColumn()-1]) && !"O".equals(map[wolf.getRow()+1][wolf.getColumn()+1])){
                return true;
            }
        }else if(wolf.getColumn() == 0){
            if(!"O".equals(map[wolf.getRow()-1][wolf.getColumn()+1]) && !"O".equals(map[wolf.getRow()+1][wolf.getColumn()+1])){
                return true;
            }
        }else if(wolf.getColumn() == 7 && wolf.getRow() != 0){
           if(!"O".equals(map[wolf.getRow()-1][wolf.getColumn()-1]) && !"O".equals(map[wolf.getRow()+1][wolf.getColumn()-1])){
                return true;
           } 
        }else{
           if(!"O".equals(map[wolf.getRow()-1][wolf.getColumn()-1]) && !"O".equals(map[wolf.getRow()+1][wolf.getColumn()-1]) && !"O".equals(map[wolf.getRow()-1][wolf.getColumn()+1]) && !"O".equals(map[wolf.getRow()+1][wolf.getColumn()+1])){
                return true;
           } 
        }
        return false;
    }
    public int getResult() {
        if(lose()){
            value = -1;
            return -1;
        }else if(win()){
            value = 1;
            return 1;
        }else 
            return 0;
    }
    public boolean isTerminal(){
        return win() || lose();
    }    
    public ArrayList<Board> getAllBoard(){
        return child;
    }

    public Wolf getWolf() {
        return wolf;
    }

    public void setWolf(Wolf wolf) {
        this.wolf = wolf;
    }

    public Sheep getSheep1() {
        return sheep1;
    }

    public void setSheep1(Sheep sheep1) {
        this.sheep1 = sheep1;
    }

    public Sheep getSheep2() {
        return sheep2;
    }

    public void setSheep2(Sheep sheep2) {
        this.sheep2 = sheep2;
    }

    public Sheep getSheep3() {
        return sheep3;
    }

    public void setSheep3(Sheep sheep3) {
        this.sheep3 = sheep3;
    }

    public Sheep getSheep4() {
        return sheep4;
    }

    public void setSheep4(Sheep sheep4) {
        this.sheep4 = sheep4;
    }
    
    public String[][] SetJogada(int x, int y) {
        map[x][y]="W";
        //System.out.println("velho: "+wolf.getRow() + " " + wolf.getColumn());
        map[this.wolf.getRow()][this.wolf.getColumn()]="O";
        //System.out.println("Posicao anterior: " + getXY(wolf.getRow(), wolf.getColumn()));
        wolf.setRow(x);
        wolf.setColumn(y);
        return map;
    }
    public ArrayList<Board> getAllMoves() throws CloneNotSupportedException {
        child = new ArrayList<>();
        
        if(player.equals(Player.Max)){
            //ovelha 1
            //ovelha 2
            //ovelha 3
            //ovelha 4
        }else{
            
        }
        return child;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if(j<7)
                    s+=map[i][j]+"    ";
                else if(j==7)
                    s+=map[i][j]+"\n\n";
                else s+="";
            }
        }
        return s+"\n"+"posicao lobo " + wolf.getRow() + " " + wolf.getColumn();
    }    
}
