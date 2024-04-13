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
public class View {
    
    public void printBoard(int [][] board){
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                char character = ' ';
                switch(board[i][y]){
                    case 1:
                        character = 'o';
                        break;
                    case 3:
                        character = 'x';  
                        break;
                }
                System.out.print(character);
                if(y<2) System.out.print("|");
            }
            
            if(i<2) System.out.println("\n-----");
        }
        System.out.println("\n");
    }
}
