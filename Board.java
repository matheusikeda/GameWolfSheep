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
public class Board implements Cloneable{
    private static final int LENGTH = 4;
    private static final int WOLF = 9;
    private static final int SHEEP1 = 1;
    private static final int SHEEP2 = 2;
    private static final int SHEEP3 = 3;
    private static final int SHEEP4 = 4;
    
    private int[][] map = new int[LENGTH][LENGTH];
    private Player player;
    private int value = 0;
    private ArrayList<Board> child = new ArrayList<>();    
    
    public void inicialize(){
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = 0;
            }
        }
        map[0][1] = WOLF;
        map[3][0] = SHEEP1;       
        map[3][2] = SHEEP2; 
//        map[7][4] = SHEEP3; 
//        map[7][6] = SHEEP4; 
    }
    
    public int[][] getMap() {
        return map;
    }
    public void setMap(int[][] map) {
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
    //verifica se computador perdeu (Lobo ganhou)
    public boolean lose(){
        int wolf[] = findPosition(WOLF);
        return wolf[0] == LENGTH-1;
    }
    //verifica se jogador perdeu (ovelhas ganharam)
    public boolean win(){            
        int wolf[] = new int[2]; 
        wolf = findPosition(WOLF);
        int row = wolf[0];
        int column = wolf[1];
        
        if(row == 0 && column == (LENGTH-1)){
            if(map[row+1][column-1] != 0){
                return true;
            }
        }else if(row == 0 && column != (LENGTH-1)){
            if(map[row+1][column-1] !=0 && map[row+1][column+1]!=0){
                return true;
            }
        }else if(column== 0){
            if(map[row-1][column+1] !=0 && map[row+1][column+1]!=0){
                return true;
            }
        }else if(column == (LENGTH-1) && row != 0){
           if(map[row-1][column-1]!=0 && map[row+1][column-1]!=0){
                return true;
           } 
        }else{
           if(map[row-1][column-1]!=0 && map[row+1][column-1]!=0 && map[row-1][column+1]!=0 && map[row+1][column+1]!=0){
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
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Board b = (Board)super.clone();
        b.map = new int[LENGTH][LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            System.arraycopy(map[i], 0, b.getMap()[i], 0, LENGTH);
        }
        return b;
    }
    public int[][] SetJogada(int x, int y) {
        int[] wolf = findPosition(WOLF);
        map[x][y]=WOLF;
        map[wolf[0]][wolf[1]]=0;
        return map;
    }
    public int[] findPosition(int piece) {
        int pos[] = new int[2];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j] == piece) {
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }             
            }            
        }
        return pos;
    }
    public ArrayList<Board> getAllMoves(Board t) throws CloneNotSupportedException {
        child = new ArrayList<>();
        if(t.player.equals(Player.Max)){
            //ovelha 1
            int sheep1[] = findPosition(SHEEP1);
            //direita
            int row = sheep1[0];
            int column = sheep1[1];
            
            row--;
            column++;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = SHEEP1;
                    newBoard.map[sheep1[0]][sheep1[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            //esquerda
            row = sheep1[0];
            column = sheep1[1];
            row--;
            column--;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = SHEEP1;
                    newBoard.map[sheep1[0]][sheep1[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            
            //ovelha 2
            int sheep2[] = findPosition(SHEEP2);
            //direita
            row = sheep2[0];
            column = sheep2[1];
            row--;
            column++;
            
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = SHEEP2;
                    newBoard.map[sheep2[0]][sheep2[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            
            //esquerda
            row = sheep2[0];
            column = sheep2[1];
            row--;
            column--;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = SHEEP2;
                    newBoard.map[sheep2[0]][sheep2[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            
            //ovelha 3
//            int sheep3[] = findPosition(SHEEP3);
//            //direita
//            row = sheep3[0];
//            column = sheep3[1];
//            row--;
//            column++;
//            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
//                if (map[row][column] == 0) {
//                    Board newBoard = (Board) t.clone();
//                    newBoard.map[row][column] = SHEEP3;
//                    newBoard.map[sheep3[0]][sheep3[1]] = 0;
//                    newBoard.getResult();
//                    child.add(newBoard);
//                }
//            }
//            //esquerda
//            row = sheep3[0];
//            column = sheep3[1];
//            row--;
//            column--;
//            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
//                if (map[row][column] == 0) {
//                    Board newBoard = (Board) t.clone();
//                    newBoard.map[row][column] = SHEEP3;
//                    newBoard.map[sheep3[0]][sheep3[1]] = 0;
//                    newBoard.getResult();
//                    child.add(newBoard);
//                }
//            }
//            
//            //ovelha 4
//            int sheep4[] = findPosition(SHEEP4);
//            //direita
//            row = sheep4[0];
//            column = sheep4[1];
//            row--;
//            column++;
//            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
//                if (map[row][column] == 0) {
//                    Board newBoard = (Board) t.clone();
//                    newBoard.map[row][column] = SHEEP4;
//                    newBoard.map[sheep4[0]][sheep4[1]] = 0;
//                    newBoard.getResult();
//                    child.add(newBoard);
//                }
//            }
//            //esquerda
//            row = sheep4[0];
//            column = sheep4[1];
//            row--;
//            column--;
//            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
//                if (map[row][column] == 0) {
//                    Board newBoard = (Board) t.clone();
//                    newBoard.map[row][column] = SHEEP4;
//                    newBoard.map[sheep4[0]][sheep4[1]] = 0;
//                    newBoard.getResult();
//                    child.add(newBoard);
//                }
//            }
            
        }else{
            int wolf[] = findPosition(WOLF);
            int row = wolf[0];
            int column = wolf[1];
            
            //acima e esquerda
            row--;
            column--;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = WOLF;
                    newBoard.map[wolf[0]][wolf[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            //acima e direita
            row = wolf[0];
            column = wolf[1];
            row--;
            column++;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = WOLF;
                    newBoard.map[wolf[0]][wolf[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            //abaixo e esquerda
            row = wolf[0];
            column = wolf[1];
            row++;
            column--;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = WOLF;
                    newBoard.map[wolf[0]][wolf[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
            //abaixo e direita
            row = wolf[0];
            column = wolf[1];
            row++;
            column++;
            if (row >= 0 && column >= 0 && row < LENGTH && column < LENGTH) {
                if (map[row][column] == 0) {
                    Board newBoard = (Board) t.clone();
                    newBoard.map[row][column] = WOLF;
                    newBoard.map[wolf[0]][wolf[1]] = 0;
                    newBoard.getResult();
                    child.add(newBoard);
                }
            }
        }
        return child;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if(j< (LENGTH-1))
                    s+=map[i][j]+"    ";
                else if(j==(LENGTH-1))
                    s+=map[i][j]+"\n\n";
                else s+="";
            }
        }
        return s;
    }    
}
