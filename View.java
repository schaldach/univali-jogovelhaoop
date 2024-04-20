/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelha;
import java.util.Scanner;

/**
 *
 * @author 8069875
 */
public class View { 

    public int[] getPlayerMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite as coordenadas da sua jogada: ");
        System.out.print("Linha: ");
        int i = scanner.nextInt();
        System.out.print("Coluna: ");
        int j = scanner.nextInt();
        int[] playerMoves = {i,j};
        return playerMoves;
    }
    
    public void printBoard(Model board){
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                char character = ' ';
                switch(board.getPosition(i,y)){
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
