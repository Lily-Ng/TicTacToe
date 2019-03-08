/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Tic-Tac-Toe game without GUI*/

package tictactoe;

/**
 *
 * @author User
 */
import java.util.Scanner;

/**
 *
 * @author Lily Ng
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welcome to Java Tic-Tac-Toe.");
        printUserManual();
        char [] gameMap = new char[9];
        GameBoardInit(gameMap);
        boolean player1Turn = true;
        int statusCode = 0;
        int turnCount = 0;
        do{
            int playerTurn = 1;
            if (player1Turn == true)
                playerTurn = 1;
            else playerTurn = 2;
            System.out.print("Player " + playerTurn + ", please pick a position to place your mark:(0-8) ");
            int playerChoice = myScanner.nextInt();
            while (placeMark(gameMap, playerChoice, player1Turn) != 0) {
                System.out.print("Position " + playerChoice + " already occupied. Please pick an available one: ");
                playerChoice = myScanner.nextInt();
            }
            statusCode = getGameStatus(gameMap);
            turnCount++;
            player1Turn = !player1Turn;
            printCurrMap(gameMap);
            playerTurn++;
        } while (statusCode != 1 && turnCount < 9);
        if (statusCode == 0 && turnCount >= 9){
            System.out.println("End of game. Game ended without winner.");
        }else{
            player1Turn = !player1Turn;
            if (player1Turn){
                System.out.println("End of game. Player 1 won.");
            } else {
                System.out.println("End of game. Player 2 won.");
            }
        }
    }
    
    public static void printUserManual(){
        System.out.println("Please refer to the game board positions by the following numbering convention:");
        System.out.println("0 1 2");
        System.out.println("3 4 5");
        System.out.println("6 7 8\n");
    }
    
    public static void GameBoardInit(char [] gameboard){
        for (int i = 0; i < 9; i++){
            gameboard[i] = '-';
        }
        return;
    }
    
    /*Returns 0 if successfully placed, 1 if there is an error*/
    public static int placeMark(char [] gameboard, int position, boolean Player1Turn){
        if (position < 0 || position > 8){
            return 1;
        }
        if (gameboard[position] == '-'){
            if (Player1Turn){
                gameboard[position] = 'X';
            } else {
                gameboard[position] = 'O';
            }
            return 0;
        }
        return 1;
    }
    
    /* Returns 0 if no winner yet
       Returns 1 if there are three in a row*/
    public static int getGameStatus(char [] gameboard){
        if ((gameboard[0] == gameboard[1] && gameboard[1] == gameboard[2] && gameboard[0] != '-' && gameboard[1] != '-' && gameboard[2] != '-')||
                (gameboard[3] == gameboard[4] && gameboard[4] == gameboard[5] && gameboard[3] != '-' && gameboard[4] != '-' && gameboard[5] != '-')||
                (gameboard[6] == gameboard[7] && gameboard[7] == gameboard[8] && gameboard[6] != '-' && gameboard[7] != '-' && gameboard[8] != '-')||
                (gameboard[0] == gameboard[3] && gameboard[3] == gameboard[6] && gameboard[0] != '-' && gameboard[3] != '-' && gameboard[6] != '-')||
                (gameboard[1] == gameboard[4] && gameboard[4] == gameboard[7] && gameboard[1] != '-' && gameboard[4] != '-' && gameboard[7] != '-')||
                (gameboard[2] == gameboard[5] && gameboard[5] == gameboard[8] && gameboard[2] != '-' && gameboard[5] != '-' && gameboard[8] != '-')||
                (gameboard[0] == gameboard[4] && gameboard[4] == gameboard[8] && gameboard[0] != '-' && gameboard[4] != '-' && gameboard[8] != '-')||
                (gameboard[2] == gameboard[4] && gameboard[4] == gameboard[6] && gameboard[2] != '-' && gameboard[4] != '-' && gameboard[6] != '-'))
            return 1;
        return 0;
    }
    
    public static void printCurrMap(char [] gameboard){
        System.out.println("Current Gameboard:");
        for (int i = 0; i < 3; i++){
            System.out.print(gameboard[i] + " ");
        }
        System.out.println();
        for (int i = 3; i < 6; i++){
            System.out.print(gameboard[i] + " ");
        }
        System.out.println();
        for (int i = 6; i < 9; i++){
            System.out.print(gameboard[i] + " ");
        }
        System.out.println();
        return;
    }
}
