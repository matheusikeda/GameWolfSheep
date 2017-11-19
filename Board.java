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
    private static final int LENGTH = 8;
    
    private String[][] map = new String[LENGTH][LENGTH];
    private Player player;
    private Wolf wolf = new Wolf(0, 3);
    private Sheep sheep1 = new Sheep(7, 0);
    private Sheep sheep2 = new Sheep(7, 2);
    private Sheep sheep3 = new Sheep(7, 4);;
    private Sheep sheep4 = new Sheep(7, 6);;
    private int value = 0;
    
    private Board father;
    private ArrayList<Board> child = new ArrayList<Board>();
    
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
    @Override
    protected Board clone() throws CloneNotSupportedException {
        Board t = new Board();
        for (int i = 0; i < LENGTH; i++) {
            System.arraycopy(map[i], 0, t.getMap()[i], 0, LENGTH);
        }
        return t;
    }
    
    public ArrayList<Board> getAllMoves(Board t) {
        child = new ArrayList<>();
            try {
                Board succ = t.clone();
                if(player.equals(Player.Max)){
                    //ovelha 1
                    if(t.sheep1.getRow() == 7 && t.sheep1.getColumn() == 0){
                        //verifica se vazio
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){    
                                //adiciona ovelha no mapa
                                succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                                //limpa posicao anterior da ovelha
                                succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                                //atualiza a posicao da ovelha
                                t.sheep1.setRow(t.sheep1.getRow()-1);
                                t.sheep1.setColumn(t.sheep1.getColumn()+1);
                                succ.getResult();
                                child.add(succ);
                            }
                    }else if(t.sheep1.getRow() == 7 && t.sheep1.getColumn() != 0){
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }   
                        
                    }else if(t.sheep1.getColumn() == 0 && t.sheep1.getRow() != 7){
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.sheep1.getColumn() == 7){
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else{
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()+1))){    
                             //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep1.getRow()-1, t.sheep1.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep1.getRow()-1, t.sheep1.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep1.getRow(),t.sheep1.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep1.setRow(t.sheep1.getRow()-1);
                            t.sheep1.setColumn(t.sheep1.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }
                    //ovelha 2
                    succ = t.clone();
                    if(t.sheep2.getRow() == 7 && t.sheep2.getColumn() == 0){
                        //verifica se vazio
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){    
                                //adiciona ovelha no mapa
                                succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                                //limpa posicao anterior da ovelha
                                succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                                //atualiza a posicao da ovelha
                                t.sheep2.setRow(t.sheep2.getRow()-1);
                                t.sheep2.setColumn(t.sheep2.getColumn()+1);
                                succ.getResult();
                                child.add(succ);
                            }
                    }else if(t.sheep2.getRow() == 7 && t.sheep2.getColumn() != 0){
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }   
                        
                    }else if(t.sheep2.getColumn() == 0 && t.sheep2.getRow() != 7){
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.sheep2.getColumn() == 7){
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else{
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()+1))){    
                             //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep2.getRow()-1, t.sheep2.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep2.getRow()-1, t.sheep2.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep2.getRow(),t.sheep2.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep2.setRow(t.sheep2.getRow()-1);
                            t.sheep2.setColumn(t.sheep2.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }
                    //sheep 3
                    succ = t.clone();
                    if(t.sheep3.getRow() == 7 && t.sheep3.getColumn() == 0){
                        //verifica se vazio
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){    
                                //adiciona ovelha no mapa
                                succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                                //limpa posicao anterior da ovelha
                                succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                                //atualiza a posicao da ovelha
                                t.sheep3.setRow(t.sheep3.getRow()-1);
                                t.sheep3.setColumn(t.sheep3.getColumn()+1);
                                succ.getResult();
                                child.add(succ);
                            }
                    }else if(t.sheep3.getRow() == 7 && t.sheep3.getColumn() != 0){
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }   
                        
                    }else if(t.sheep3.getColumn() == 0 && t.sheep3.getRow() != 7){
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.sheep3.getColumn() == 7){
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else{
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()+1))){    
                             //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep3.getRow()-1, t.sheep3.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep3.getRow()-1, t.sheep3.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep3.getRow(),t.sheep3.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep3.setRow(t.sheep3.getRow()-1);
                            t.sheep3.setColumn(t.sheep3.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }
                    //ovelha 4
                    succ = t.clone();
                    if(t.sheep4.getRow() == 7 && t.sheep4.getColumn() == 0){
                        //verifica se vazio
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){    
                                //adiciona ovelha no mapa
                                succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                                //limpa posicao anterior da ovelha
                                succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                                //atualiza a posicao da ovelha
                                t.sheep4.setRow(t.sheep4.getRow()-1);
                                t.sheep4.setColumn(t.sheep4.getColumn()+1);
                                succ.getResult();
                                child.add(succ);
                            }
                    }else if(t.sheep4.getRow() == 7 && t.sheep4.getColumn() != 0){
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }   
                        
                    }else if(t.sheep4.getColumn() == 0 && t.sheep4.getRow() != 7){
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.sheep4.getColumn() == 7){
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){    
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else{
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()+1))){    
                             //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()+1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.sheep4.getRow()-1, t.sheep4.getColumn()-1))){  
                            succ = t.clone();
                            //adiciona ovelha no mapa
                            succ.setS(t.sheep4.getRow()-1, t.sheep4.getColumn()-1);
                            //limpa posicao anterior da ovelha
                            succ.setO(t.sheep4.getRow(),t.sheep4.getColumn());
                            //atualiza a posicao da ovelha
                            t.sheep4.setRow(t.sheep4.getRow()-1);
                            t.sheep4.setColumn(t.sheep4.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }
                //movimentos do lobo
                }else{
                    if(t.wolf.getRow() == 0 && t.wolf.getColumn() == 7){
                        //verifica diagonal esquerda inferior vazia
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                            //adiciona lobo no mapa
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                            //limpa posicao anterior do lobo
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            //atualiza a posicao do lobo
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.wolf.getRow() == 0 && t.wolf.getColumn() != 7){
                        //verifica diagonal direita inferior vazia
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        //verifica diagonal esquerda inferior vazia
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        } 
                    }else if(t.wolf.getColumn() == 0){
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.wolf.getRow()-1, t.wolf.getColumn()+1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()-1, t.wolf.getColumn()+1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()-1);
                            t.wolf.setColumn(t.wolf.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }else if(t.wolf.getColumn() == 7 && t.wolf.getRow() != 0){
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.wolf.getRow()-1, t.wolf.getColumn()-1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()-1, t.wolf.getColumn()-1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()-1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                        
                    }else{
                        if("O".equals(succ.getXY(t.wolf.getRow()-1, t.wolf.getColumn()-1))){
                            succ.setW(t.wolf.getRow()-1, t.wolf.getColumn()-1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()-1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.wolf.getRow()-1, t.wolf.getColumn()+1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()-1, t.wolf.getColumn()+1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()-1);
                            t.wolf.setColumn(t.wolf.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()-1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()-1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()-1);
                            succ.getResult();
                            child.add(succ);
                        }
                        if("O".equals(succ.getXY(t.wolf.getRow()+1, t.wolf.getColumn()+1))){
                            succ = t.clone();
                            succ.setW(t.wolf.getRow()+1, t.wolf.getColumn()+1);
                            succ.setO(t.wolf.getRow(),t.wolf.getColumn());
                            t.wolf.setRow(t.wolf.getRow()+1);
                            t.wolf.setColumn(t.wolf.getColumn()+1);
                            succ.getResult();
                            child.add(succ);
                        }
                    }
                }
            } catch (CloneNotSupportedException e) {
                    System.out.println(e);
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
        return s+"\n";
    }
    public String[][] SetJogada(int x, int y) {
        this.map[x][y]="W";
        this.map[this.wolf.getRow()][this.wolf.getColumn()]="O";
        this.wolf.setRow(x);
        this.wolf.setColumn(y);
        return map;
    }
    
    
    
    
}
