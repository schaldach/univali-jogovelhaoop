/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelha;

/**
 *
 * @author 8069875
 */
public class Model {
    // tabuleiro totalmente vazio
    private int[][] gameBoard = {{2,2,2},{2,2,2},{2,2,2}};
    
    public void resetBoard(){
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                gameBoard[i][y] = 2;
            }
        }
    }
    // falso se os valores não forem os esperados
    public boolean setPosition(int y, int x, int value){
        if(y<0 || y>2 || x<0 || x>2 || value <1 || value >3 ) return false;
        gameBoard[y][x] = value;
        return true;
    }
    
    public int getPosition(int y, int x){
        if(y<0 || y>2 || x<0 || x>2) return 0;
        return gameBoard[y][x];
    }
    
    public int[][] getBoard(){
        return gameBoard;
    }
}
