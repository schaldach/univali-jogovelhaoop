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
public class Control {
    private Model model = new Model();
    private View view = new View();
    private boolean currentPlayerIsUser = true;
    Scanner scanner = new Scanner(System.in);

    public void startGame(){
        while(model.checkIfWinner() == 0 || model.checkIfWinner() == 2){
            if(currentPlayerIsUser){
                view.printBoard(model.getBoard());
                System.out.println("Digite as coordenadas da sua jogada: ");
                System.out.print("Linha: ");
                int i = scanner.nextInt();
                System.out.print("Coluna: ");
                int j = scanner.nextInt();
                model.setPosition(i,j,3);
                view.printBoard(model.getBoard());
                currentPlayerIsUser = false;
            }
            else{
                System.out.println("A máquina irá jogar");
                int [] bestMove = model.getBestMove();
                model.setPosition(bestMove[0],bestMove[1],1);
                currentPlayerIsUser = true;
            }
        }
        String winner = "";
        if(model.checkIfWinner() == 3) winner = "Player 1";
        if(model.checkIfWinner() == 1) winner = "Player 2 (Máquina)";
        view.printBoard(model.getBoard());
        System.out.println("O vencedor foi: "+winner);
    }
}
