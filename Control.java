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
public class Control {
    private Model model = new Model();
    private View view = new View();
    private boolean currentPlayerIsUser = true;

    public void startGame(){
        while(checkIfWinner(model) == 0 || checkIfWinner(model) == 2){
            if(currentPlayerIsUser){
                view.printBoard(model);
                
                boolean rightPosition = false;
                while(!rightPosition){
                    int[] playerMove = view.getPlayerMove();
                    if(model.getPosition(playerMove[0],playerMove[1]) != 2) continue;
                    rightPosition = model.setPosition(playerMove[0],playerMove[1],3);
                }
                
                view.printBoard(model);
                currentPlayerIsUser = false;
            }
            else{
                System.out.println("A máquina irá jogar");
                int [] bestMove = getBestMove(model);
                model.setPosition(bestMove[0],bestMove[1],1);
                currentPlayerIsUser = true;
            }
        }
        String winner = "";
        if(checkIfWinner(model) == 3) winner = "Player 1";
        if(checkIfWinner(model) == 1) winner = "Player 2 (Máquina)";
        view.printBoard(model);
        System.out.println("O vencedor foi: "+winner);
    }
    
    public int getScore(Model gameBoard){
        int totalScore = 0;
        
        // linhas
        for(int i=0;i<3;i++){
            int rowProduct = 1;
            for(int y=0;y<3;y++){
                rowProduct *= gameBoard.getPosition(i,y);
            }
            totalScore += rowProduct;
        }
        //colunas
        for(int i=0;i<3;i++){
            int columnProduct = 1;
            for(int y=0;y<3;y++){
                columnProduct *= gameBoard.getPosition(y,i);
            }
            totalScore += columnProduct;
        }
        // diagonais
        int diagonal1Product = 1;
        for(int i=0;i<3;i++){
            diagonal1Product *= gameBoard.getPosition(i,i);
        }
        totalScore += diagonal1Product;
        
        int diagona21Product = 1;
        for(int i=0;i<3;i++){
            diagona21Product *= gameBoard.getPosition(i,2-i);
        }
        totalScore += diagona21Product;
        
        return totalScore;
    }
    
    public boolean isCriticMove(int y, int x, Model gameBoard){
        int rowProduct = 1;
        for(int i=0;i<3;i++){
            rowProduct *= gameBoard.getPosition(y,i);
        }
        if(rowProduct==9) return true;
        
        int columnProduct = 1;
        for(int i=0;i<3;i++){
            columnProduct *= gameBoard.getPosition(i,x);
        }
        if(columnProduct==9) return true;
        
        // está na diagonal primária
        if(y==x){
            int diagonalProduct = 1;
            for(int i=0;i<3;i++){
                diagonalProduct *= gameBoard.getPosition(i,i);
            }
            if(diagonalProduct==9) return true;
        }
        
        // está na diagonal secundária
        if((y==0&&x==2) || (x==0&&y==2) || (y==1&&x==1)){
            int diagonalProduct = 1;
            for(int i=0;i<3;i++){
                diagonalProduct *= gameBoard.getPosition(i,2-i);
            }
            if(diagonalProduct==9) return true;
        }
        return false;
    }
    
    public int[] getBestMove(Model gameBoard){
        int[] bestMove = new int[2];
        int lowestScore = 216;
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                if(gameBoard.getPosition(i,y)==2){
                    gameBoard.setPosition(i,y,1);
                    if(getScore(gameBoard)<lowestScore){
                        lowestScore = getScore(gameBoard);
                        bestMove[0] = i;
                        bestMove[1] = y;
                    }
                    else if(getScore(gameBoard)==lowestScore){
                        if(isCriticMove(i,y, gameBoard)){
                            lowestScore = getScore(gameBoard);
                            bestMove[0] = i;
                            bestMove[1] = y;
                        }
                    }
                    gameBoard.setPosition(i,y,2);
                }
            }
        }
        return bestMove;
    }
    
    public int checkIfWinner(Model gameBoard){        
        // linhas
        for(int i=0;i<3;i++){
            if(gameBoard.getPosition(i,0) == gameBoard.getPosition(i,1) && gameBoard.getPosition(i,0) == gameBoard.getPosition(i,2) )
            return gameBoard.getPosition(i,0);
        }
        //colunas
        for(int i=0;i<3;i++){
            if(gameBoard.getPosition(0,i) == gameBoard.getPosition(1,i) && gameBoard.getPosition(0,i) == gameBoard.getPosition(2,i) )
            return gameBoard.getPosition(0,i);
        }

        // diagonais
        if(gameBoard.getPosition(0,0) == gameBoard.getPosition(1,1) && gameBoard.getPosition(0,0) == gameBoard.getPosition(2,2)) return gameBoard.getPosition(0,0);
        if(gameBoard.getPosition(0,2) == gameBoard.getPosition(1,1) && gameBoard.getPosition(0,2) == gameBoard.getPosition(2,0)) return gameBoard.getPosition(0,2);   
        
        return 0;
    }
}
