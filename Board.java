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
public class Board {
    private static final int LENGTH = 8;
    
    private String[][] map = new String[LENGTH][LENGTH];
    private Player player;
    private Wolf wolf = new Wolf(0,3);
    private Sheep sheep1 = new Sheep(7,0);
    private Sheep sheep2 = new Sheep(7,2);
    private Sheep sheep3 = new Sheep(7,4);
    private Sheep sheep4 = new Sheep(7,6);
    private int value = 0;
    
    private Board father;
    private ArrayList<Board> child = new ArrayList<Board>();

    public Board() {
        
    }
    public Board(Board b) {
        this.wolf = new Wolf(b.wolf);
        this.sheep1 = new Sheep(b.sheep1);
        this.sheep2 = new Sheep(b.sheep2);
        this.sheep3 = new Sheep(b.sheep3);
        this.sheep4 = new Sheep(b.sheep4);
        this.player = b.player;
        this.value = b.value;
        this.map = new String[LENGTH][LENGTH];
        System.arraycopy(b.map, 0, map, 0, LENGTH);
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
        return wolf.getRow() == 7;
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
    
    public ArrayList<Board> getAllMoves(Board t) {

        child = new ArrayList<>();
        if(player.equals(Player.Max)){
            //ovelha 1
            if(t.sheep1.getRow() == 7 && t.sheep1.getColumn() == 0){
                //verifica se vazio
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep1.setRow(t.sheep1.getRow()-1);
                    succ.sheep1.setColumn(t.sheep1.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep1.getRow() == 7 && t.sheep1.getColumn() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep1.setRow(t.sheep1.getRow()-1);
                    succ.sheep1.setColumn(t.sheep1.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep1.setRow(t.sheep1.getRow()-1);
                    succ2.sheep1.setColumn(t.sheep1.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
                
            }else if(t.sheep1.getColumn() == 0 && t.sheep1.getRow() != 7){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep1.setRow(t.sheep1.getRow()-1);
                    succ.sheep1.setColumn(t.sheep1.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep1.getColumn() == 7 && t.sheep1.getRow() != 7 && t.sheep1.getRow() != 0){
                Board succ = new Board(t);
                System.out.println("antes"+t.sheep1.getRow()+t.sheep1.getColumn());
                if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep1.setRow(t.sheep1.getRow()-1);
                    succ.sheep1.setColumn(t.sheep1.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }    
            }else if(t.sheep1.getRow() == 0){

            }else{
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep1.setRow(t.sheep1.getRow()-1);
                    succ.sheep1.setColumn(t.sheep1.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep1.setRow(t.sheep1.getRow()-1);
                    succ2.sheep1.setColumn(t.sheep1.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
            }
            //ovelha 2
            if(t.sheep2.getRow() == 7 && t.sheep2.getColumn() == 0){
                //verifica se vazio
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep2.setRow(t.sheep2.getRow()-1);
                    succ.sheep2.setColumn(t.sheep2.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep2.getRow() == 7 && t.sheep2.getColumn() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep2.setRow(t.sheep2.getRow()-1);
                    succ.sheep2.setColumn(t.sheep2.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep2.setRow(t.sheep2.getRow()-1);
                    succ2.sheep2.setColumn(t.sheep2.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
                
            }else if(t.sheep2.getColumn() == 0 && t.sheep2.getRow() != 7){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep2.setRow(t.sheep2.getRow()-1);
                    succ.sheep2.setColumn(t.sheep2.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep2.getColumn() == 7 && t.sheep1.getRow() != 7 && t.sheep2.getRow() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep2.setRow(t.sheep2.getRow()-1);
                    succ.sheep2.setColumn(t.sheep2.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
            }else if(t.sheep2.getRow() == 0){
    
            }else{
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep2.setRow(t.sheep2.getRow()-1);
                    succ.sheep2.setColumn(t.sheep2.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                
                if("O".equals(succ2.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep2.setRow(t.sheep2.getRow()-1);
                    succ2.sheep2.setColumn(t.sheep2.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
            }
            //sheep 3
            if(t.sheep3.getRow() == 7 && t.sheep3.getColumn() == 0){
                //verifica se vazio
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep3.setRow(t.sheep3.getRow()-1);
                    succ.sheep3.setColumn(t.sheep3.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep3.getRow() == 7 && t.sheep3.getColumn() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep3.setRow(t.sheep3.getRow()-1);
                    succ.sheep3.setColumn(t.sheep3.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep3.setRow(t.sheep3.getRow()-1);
                    succ2.sheep3.setColumn(t.sheep3.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
                
            }else if(t.sheep3.getColumn() == 0 && t.sheep3.getRow() != 7){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep3.setRow(t.sheep3.getRow()-1);
                    succ.sheep3.setColumn(t.sheep3.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep3.getColumn() == 7 && t.sheep1.getRow() != 7 && t.sheep3.getRow() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep3.setRow(t.sheep3.getRow()-1);
                    succ.sheep3.setColumn(t.sheep3.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
            }else if(t.sheep3.getRow() == 0){
    
            }else{
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep3.setRow(t.sheep3.getRow()-1);
                    succ.sheep3.setColumn(t.sheep3.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep3.setRow(t.sheep3.getRow()-1);
                    succ2.sheep3.setColumn(t.sheep3.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
            }
            //ovelha 4
            if(t.sheep4.getRow() == 7 && t.sheep4.getColumn() == 0){
                //verifica se vazio
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep4.setRow(t.sheep4.getRow()-1);
                    succ.sheep4.setColumn(t.sheep4.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep4.getRow() == 7 && t.sheep4.getColumn() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep4.setRow(t.sheep4.getRow()-1);
                    succ.sheep4.setColumn(t.sheep4.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    System.out.println("antes"+succ2.sheep4.getRow() + " " + succ2.sheep4.getColumn());
                    succ2.sheep4.setRow(t.sheep4.getRow()-1);
                    succ2.sheep4.setColumn(t.sheep4.getColumn()-1);
                    System.out.println("depois"+succ2.sheep4.getRow() + " " + succ2.sheep4.getColumn());
                    succ2.getResult();
                    child.add(succ2);
                }
                
                
            }else if(t.sheep4.getColumn() == 0 && t.sheep4.getRow() != 7){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep4.setRow(t.sheep4.getRow()-1);
                    succ.sheep4.setColumn(t.sheep4.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.sheep4.getColumn() == 7 && t.sheep1.getRow() != 7 && t.sheep4.getRow() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep4.setRow(t.sheep4.getRow()-1);
                    succ.sheep4.setColumn(t.sheep4.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
            }else if(t.sheep4.getRow() == 0){
    
            }else{
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){
                    //adiciona ovelha no mapa
                    succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                    //limpa posicao anterior da ovelha
                    succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ.sheep4.setRow(t.sheep4.getRow()-1);
                    succ.sheep4.setColumn(t.sheep4.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){
                    //adiciona ovelha no mapa
                    succ2.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                    //limpa posicao anterior da ovelha
                    succ2.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                    //atualiza a posicao da ovelha
                    succ2.sheep4.setRow(t.sheep4.getRow()-1);
                    succ2.sheep4.setColumn(t.sheep4.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2 );
                }
            }
            //movimentos do lobo
        }else{
            if(t.wolf.getRow() == 7){
                
            }
            if(t.wolf.getRow() == 0 && t.wolf.getColumn() == 7){
                //verifica diagonal esquerda inferior vazia
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                    //adiciona lobo no
                    succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                    //limpa posicao anterior do lobo
                    succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                    //atualiza a posicao do lobo
                    succ.wolf.setRow(t.wolf.getRow()+1);
                    succ.wolf.setColumn(t.wolf.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
                
            }else if(t.wolf.getRow() == 0 && t.wolf.getColumn() != 7){
                //verifica diagonal direita inferior vazia
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                    succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                    succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ.wolf.setRow(t.wolf.getRow()+1);
                    succ.wolf.setColumn(t.wolf.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                //verifica diagonal esquerda inferior vazia
                if("O".equals(succ2.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                    succ2.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                    succ2.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ2.wolf.setRow(t.wolf.getRow()+1);
                    succ2.wolf.setColumn(t.wolf.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
            }else if(t.wolf.getColumn() == 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                    succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                    succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ.wolf.setRow(t.wolf.getRow()+1);
                    succ.wolf.setColumn(t.wolf.getColumn()+1);
                    succ.getResult();
                    child.add(succ);
                }
                
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.wolf.getRow()-1, t.wolf.getColumn()+1))){
                    succ2.setW(t.wolf.getRow()-1, t.wolf.getColumn()+1);
                    succ2.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ2.wolf.setRow(t.wolf.getRow()-1);
                    succ2.wolf.setColumn(t.wolf.getColumn()+1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
            }else if(t.wolf.getColumn() == 7 && t.wolf.getRow() != 0){
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                    succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                    succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ.wolf.setRow(t.wolf.getRow()+1);
                    succ.wolf.setColumn(t.wolf.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.wolf.getRow()-1, t.wolf.getColumn()-1))){
                    succ2.setW(t.wolf.getRow()-1, t.wolf.getColumn()-1);
                    succ2.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ2.wolf.setRow(t.wolf.getRow()-1);
                    succ2.wolf.setColumn(t.wolf.getColumn()-1);
                    succ2.getResult();
                    child.add(succ2);
                }
                
                
            }else{
                Board succ = new Board(t);
                if("O".equals(succ.getXY(t.wolf.getRow()-1, t.wolf.getColumn()-1))){
                    succ.setW(t.wolf.getRow()-1, t.wolf.getColumn()-1);
                    succ.setO(t.wolf.getRow(),t.wolf.getColumn()); 
                    succ.wolf.setRow(t.wolf.getRow()-1);
                    succ.wolf.setColumn(t.wolf.getColumn()-1);
                    succ.getResult();
                    child.add(succ);
                }
                Board succ2 = new Board(t);
                if("O".equals(succ2.getXY(t.wolf.getRow()-1, t.wolf.getColumn()+1))){
                    succ2.setW(t.wolf.getRow()-1, t.wolf.getColumn()+1);
                    succ2.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ2.wolf.setRow(t.wolf.getRow()-1);
                    succ2.wolf.setColumn(t.wolf.getColumn()+1);
                    succ2.getResult();
                    child.add(succ2);
                }                  
                Board succ3 = new Board(t);
                if("O".equals(succ3.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                    succ3.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                    succ3.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ3.wolf.setRow(t.wolf.getRow()+1);
                    succ3.wolf.setColumn(t.wolf.getColumn()-1);
                    succ3.getResult();
                    child.add(succ3);
                }
                Board succ4 = new Board(t);
                if("O".equals(succ4.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                    succ4.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                    succ4.setO(t.wolf.getRow(),t.wolf.getColumn());
                    succ4.wolf.setRow(t.wolf.getRow()+1);
                    succ4.wolf.setColumn(t.wolf.getColumn()+1);
                    succ4.getResult();
                    child.add(succ4);
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
                if(j<7)
                    s+=map[i][j]+"    ";
                else if(j==7)
                    s+=map[i][j]+"\n\n";
                else s+="";
            }
        }
        return s+"\n"+" posicao lobo" + wolf.getRow() + " " + wolf.getColumn();
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
    
    
    
    
}
