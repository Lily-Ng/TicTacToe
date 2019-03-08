/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Lily Ng
 * thn248@nyu.edu
 * 
 */

public class TicTacToeGUI {
    
    boolean player1Turn = true;
    int buttonAmt = 9;
    JButton[] buttons = new JButton[buttonAmt];
    Box[] gameboard = new Box[buttonAmt];

    public TicTacToeGUI(){
        JFrame jf = new JFrame("TicTacToe Game");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        GridLayout myGridLayout = new GridLayout(3,3,5,5);
        jf.setLayout(myGridLayout);
        
        // used for generating random color
        for (int i=0; i<buttonAmt; i++){
            buttons[i] = new JButton("");
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(new ButtonAction());
            buttons[i].setSelected(false);
            gameboard[i] = new Box();
        }
        
        for (int i=0; i<buttonAmt; i++){
            jf.add(buttons[i]);
        }

        jf.setVisible(true);
    }
    
    public int getGameStatus(Box[] mygame){
        if ((mygame[0].getMark() == mygame[1].getMark() && mygame[1].getMark() == mygame[2].getMark() && mygame[0].marked && mygame[1].marked && mygame[2].marked)||
                (mygame[3].getMark() == mygame[4].getMark() && mygame[4].getMark() == mygame[5].getMark() && mygame[3].marked && mygame[4].marked && mygame[5].marked)||
                (mygame[6].getMark() == mygame[7].getMark() && mygame[7].getMark() == mygame[8].getMark() && mygame[6].marked && mygame[7].marked && mygame[8].marked)||
                (mygame[0].getMark() == mygame[3].getMark() && mygame[3].getMark() == mygame[6].getMark() && mygame[0].marked && mygame[3].marked && mygame[6].marked)||
                (mygame[1].getMark() == mygame[4].getMark() && mygame[4].getMark() == mygame[7].getMark() && mygame[1].marked && mygame[4].marked && mygame[7].marked)||
                (mygame[2].getMark() == mygame[5].getMark() && mygame[5].getMark() == mygame[8].getMark() && mygame[2].marked && mygame[5].marked && mygame[8].marked)||
                (mygame[0].getMark() == mygame[4].getMark() && mygame[4].getMark() == mygame[8].getMark() && mygame[0].marked && mygame[4].marked && mygame[8].marked)||
                (mygame[2].getMark() == mygame[4].getMark() && mygame[4].getMark() == mygame[6].getMark() && mygame[2].marked && mygame[4].marked && mygame[6].marked)){
            return 1;
        } else if (mygame[0].marked && mygame[1].marked && mygame[2].marked && mygame[3].marked && mygame[4].marked && mygame[5].marked && mygame[6].marked && mygame[7].marked && mygame[8].marked){
            return -1;
        } else
        return 0;
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        new TicTacToeGUI();
    }
 
    class ButtonAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton target = (JButton) e.getSource();
        target.setSelected(true);
        for (int i=0; i<buttonAmt; i++){
            if (buttons[i].isSelected()){
                if (player1Turn && gameboard[i].placeMark(new X())){
                    buttons[i].setText(gameboard[i].getMark()+"");
                    player1Turn = !player1Turn;
                    
                } else {
                    if ((!player1Turn) && gameboard[i].placeMark(new O())){
                        buttons[i].setText(gameboard[i].getMark()+"");
                        player1Turn = !player1Turn;
                    }
                }
            }
        }
        int statusCode;
        statusCode = getGameStatus(gameboard);
        if (statusCode == 1){
            if (!player1Turn){
                System.out.println("Winner: Player 1");
            } else {
                System.out.println("Winner: Player 2");
            }
            for (int i = 0; i<buttonAmt; i++){
                buttons[i].setEnabled(false);
            }
        }
        if (statusCode == -1){
            System.out.println("GAME END WITH NO WINNER.");
        }
        target.setSelected(false);
    }
    }
}

class Box{
    char mark;
    boolean marked;
    
    Box(){
        marked = false;
    }
    
    boolean placeMark(aMark PlayerMark){
        if (marked == true){
            return false;
        } else {
            mark = PlayerMark.returnMark();
            marked = true;
            return true;
        }
    }
    
    char getMark(){
        return mark;
    }
}

interface aMark{
    char returnMark();
}

class X extends Box implements aMark{
    @Override
    public char returnMark(){
        return 'X';
    }
}

class O extends Box implements aMark{
    @Override
    public char returnMark(){
        return 'O';
    }
}