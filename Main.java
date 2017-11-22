/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.ia.gamewolfsheep;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Matheus Ikeda
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO code application logic here
        Board board = new Board();
        Wolf w = new Wolf(0,3);
        Sheep s1 = new Sheep(7,0);
        Sheep s2 = new Sheep(7,2);
        Sheep s3 = new Sheep(7,4);
        Sheep s4 = new Sheep(7,6);
        String[][] map = new String[8][8];
        
        board.setMap(map);
        
        board.setWolf(w);
        board.setSheep1(s1);
        board.setSheep2(s2);
        board.setSheep3(s3);
        board.setSheep4(s4);
        
        MiniMax minimax = new MiniMax();
        Scanner ler = new Scanner(System.in);
        
        int row, column;
        int teste=0;
        board.inicialize();
        board.iniciaArray(board);
        //System.out.println("Game\n\n" + board.toString());
        //do{
            //Jogada Lobo
//            board.setPlayer(Player.Min);
//            System.out.println("Insira posicao lobo: linha\n");
//            row = ler.nextInt();
//            System.out.println("Insira posicao lobo: coluna\n");
//            column = ler.nextInt();    
//            board.SetJogada(row, column);
//            System.out.println("Game\n\n" + board.toString());
            //Jogada Ovelha
            board.setPlayer(Player.Max);
           // ArrayList<Board> filhos = board.getAllMoves();
//            for (int i=0;i<filhos.size();i++){
//                
//                System.out.println("entrei");
//                System.out.println(filhos.get(i).toString());
//            }
            board = minimax.minimaxDecision(board);
            System.out.println("Game\n\n" + board.toString());
            //teste++;
        //}while(teste<10);
        
    }
    
}
