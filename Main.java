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

    public static void main(String[] args) {
        // TODO code application logic here
        Board board = new Board();
        MiniMax minimax = new MiniMax();
        Scanner ler = new Scanner(System.in);
        board.inicialize();
        int row, column;
        int teste=0;
        System.out.println("Game\n\n" + board.toString());
        do{
            //Jogada Lobo
            board.setPlayer(Player.Min);
            System.out.println("Insira posicao lobo: linha\n");
            row = ler.nextInt();
            System.out.println("Insira posicao lobo: coluna\n");
            column = ler.nextInt();    
            board.SetJogada(row, column);
            System.out.println("Game\n\n" + board.toString());
            //Jogada Ovelha
            board.setPlayer(Player.Max);
            board = minimax.minimaxDecision(board);
            System.out.println("Game\n\n" + board.toString());
            teste++;
        }while(teste<10);
        
    }
    
}
