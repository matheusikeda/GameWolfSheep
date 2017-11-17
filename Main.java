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
public class Main {

    
    public static void main(String[] args) {
        // TODO code application logic here
        Board board = new Board();
        board.inicialize();
        System.out.println("Game\n\n" + board.toString());
    }
    
}
