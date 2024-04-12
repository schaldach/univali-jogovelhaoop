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
    private int[][] gameBoard = {{2,2,2},{2,2,2},{2,2,2}};
    
    public int getScore(){
        int totalScore = 0;
        
        // linhas
        for(int i=0;i<3;i++){
            int rowProduct = 1;
            for(int y=0;y<3;y++){
                rowProduct *= gameBoard[i][y];
            }
            totalScore += rowProduct;
        }
        //colunas
        for(int i=0;i<3;i++){
            int columnProduct = 1;
            for(int y=0;y<3;y++){
                columnProduct *= gameBoard[y][i];
            }
            totalScore += columnProduct;
        }
        // diagonais
        int diagonal1Product = 1;
        for(int i=0;i<3;i++){
            diagonal1Product *= gameBoard[i][i];
        }
        totalScore += diagonal1Product;
        
        int diagona21Product = 1;
        for(int i=0;i<3;i++){
            diagona21Product *= gameBoard[i][2-i];
        }
        totalScore += diagona21Product;
        
        return totalScore;
    }
    
    public int[] getBestMove(){
        int[] bestMove = new int[2];
        int lowestScore = 216;
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                if(gameBoard[i][y]==2){
                    gameBoard[i][y]=1;
                    if(getScore()<lowestScore){
                        lowestScore = getScore();
                        bestMove[0] = i;
                        bestMove[1] = y;
                    }
                    gameBoard[i][y]=2;
                }
            }
        }
        return bestMove;
    }
    
    public void setPosition(int y, int x, int value){
        gameBoard[y][x] = value;
    }
    
    public int[][] getBoard(){
        return gameBoard;
    }
}
