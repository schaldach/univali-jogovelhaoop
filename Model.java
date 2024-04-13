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
    
    public int checkIfWinner(){        
        // linhas
        for(int i=0;i<3;i++){
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] )
            return gameBoard[i][0];
        }
        //colunas
        for(int i=0;i<3;i++){
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i] )
            return gameBoard[0][i];
        }

        // diagonais
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]) return gameBoard[0][0];
        if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]) return gameBoard[0][2];   
        
        return 0;
    }
    
    public void setPosition(int y, int x, int value){
        gameBoard[y][x] = value;
    }
    
    public int[][] getBoard(){
        return gameBoard;
    }
}
