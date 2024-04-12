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
public class Jogovelha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] bestMove;
        Model model = new Model();
        
        model.setPosition(0,0,3);
        
        bestMove = model.getBestMove();
        model.setPosition(bestMove[0],bestMove[1],1);
        
        model.setPosition(0,1,3);

        bestMove = model.getBestMove();
        model.setPosition(bestMove[0],bestMove[1],1);

        model.setPosition(1,2,3);

        bestMove = model.getBestMove();
        model.setPosition(bestMove[0],bestMove[1],1);

        View view = new View();
        view.printBoard(model.getBoard());
    }
    
}
