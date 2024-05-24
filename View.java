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
    // pegar o input se o jogador quer ou não continuar jogando
    public boolean getPlayerInput(){
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while(answer != 0 && answer != 1){
            System.out.println("Você quer continuar jogando? (0 para não, 1 para sim) ");
            answer = scanner.nextInt();
        }
        if(answer == 0) return false;
        else return true;
    }
    // pegar o input se o jogador quer começar jogando ou jogar depois
    public boolean getPlayerMoveOrder(){
        Scanner scanner = new Scanner(System.in);
        int answer = -1;
        while(answer != 1 && answer != 2){
            System.out.println("Você quer jogar primeiro? (1 para jogar primeiro, 2 para jogar em segundo) ");
            answer = scanner.nextInt();
        }
        if(answer == 1) return true;
        return false;

    }
    // imprimir cada posição do tabuleiro (1=máquina, 2=vazio, 3=jogador)
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

    public void printPositionEmptyError(){
        System.out.println("A posição não é válida. A posição escolhida está preenchida.");
    }

    public void printPositionNumberError(){
        System.out.println("A posição não é válida. Digite um número entre 0 e 2.");
    }
    
    public void printAIMessage(){
        System.out.println("A máquina irá jogar");
    }
    // anunciar o vencedor
    public void printFinalMessage(int winner){
        
        if(winner!=0){
            String winnerName = "";
            if(winner == 3) winnerName = "Player 1";
            if(winner == 1) winnerName = "Player 2 (Máquina)";
            System.out.println("O vencedor foi: "+winnerName);
        }
        else{
            System.out.println("O jogo empatou");
        }
    }
}
