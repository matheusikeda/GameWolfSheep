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
import java.util.ArrayList;

/**
 *
 * @author Matheus Ikeda
 */
public class MiniMax {
    
    private int alfa = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;

    public Board minimaxDecision(Board tabuleiro) throws CloneNotSupportedException {
        int melhor = MaxValue(tabuleiro);
        ArrayList<Board> filhos = tabuleiro.getAllBoard();
        for (Board filho : filhos) {
            filho.toString();
            System.out.println("");
            if (filho.getValue() == melhor) {
                return filho;
            }
        }
        return null;
    }

    private int MinValue(Board board) throws CloneNotSupportedException {
        if (board.isTerminal()) {
            board.setValue(board.getResult());
            return board.getValue();
        } else {
            board.setValue(Integer.MAX_VALUE);
            board.setPlayer(Player.Min);
            ArrayList<Board> filhos = board.getAllMoves(board);
            for (Board filho : filhos) {
                int minValue = Math.min(board.getValue(), MaxValue(filho));
                //Corte alfa beta
                if (minValue < alfa) {
                    board.setValue(minValue);
                    return board.getValue();
                }
                board.setValue(minValue);
            }
            if (board.getValue() < beta) {
                beta = board.getValue();
            }
            return board.getValue();
        }
    }

    private int MaxValue(Board board) throws CloneNotSupportedException {
        if (board.isTerminal()) {
            board.setValue(board.getResult());
            return board.getValue();
        } else {
            board.setValue(Integer.MIN_VALUE);
            board.setPlayer(Player.Max);
            ArrayList<Board> filhos = board.getAllMoves(board);
            for (Board filho : filhos) {
                int maxValue = Math.max(board.getValue(), MinValue(filho));
                //Corte alfa beta
                if (maxValue > beta) {
                    board.setValue(maxValue);
                    return board.getValue(); 
                }
                board.setValue(maxValue);
            }
            if (board.getValue() > alfa) {
                alfa = board.getValue();
            }
            return board.getValue();
        }
    }
}
