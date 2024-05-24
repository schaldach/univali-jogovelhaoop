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

    // método para iniciar um jogo e pedir para o usuário jogar novamente após o seu fim
    public void gameLoop(){
        boolean gameRunning = true;
        while(gameRunning){
            model.resetBoard();
            currentPlayerIsUser = view.getPlayerMoveOrder();
            startGame();
            gameRunning = view.getPlayerInput();
        }
    }

    public void startGame(){
        view.printBoard(model);
        // se não há vencedores e não há empate, o jogo continua
        while(checkIfWinner(model) == 0 && !checkIfDraw(model)){
            if(currentPlayerIsUser){

                boolean rightPosition = false;
                while(!rightPosition){
                    int[] playerMove = view.getPlayerMove();
                    // verificando se a posição já está preenchida
                    if(model.getPosition(playerMove[0],playerMove[1]) != 2) {
                        view.printPositionEmptyError();
                        continue;
                    }
                    // verificando se a posição está dentro dos limites (0 a 2)
                    rightPosition = model.setPosition(playerMove[0],playerMove[1],3);
                    if(!rightPosition) view.printPositionNumberError();
                }
                
                view.printBoard(model);
                currentPlayerIsUser = false;
            }
            else{
                view.printAIMessage();
                int [] bestMove = getBestMove(model);
                model.setPosition(bestMove[0],bestMove[1],1);
                
                view.printBoard(model);
                currentPlayerIsUser = true;
            }
        }
        view.printFinalMessage(checkIfWinner(model));
    }

    // soma dos produtos de todas as linhas e colunas do tabuleiro
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

    // verificando se o movimento irá ganhar o jogo para qualquer um dos jogadores, retornará para qual jogador ele ganha (3=jogador, 1=máquina)
    public int isCriticMove(int y, int x, Model gameBoard){
        int rowProduct = 1;
        for(int i=0;i<3;i++){
            rowProduct *= gameBoard.getPosition(y,i);
        }
        if(rowProduct==18) return 3;
        if(rowProduct==2) return 1;

        int columnProduct = 1;
        for(int i=0;i<3;i++){
            columnProduct *= gameBoard.getPosition(i,x);
        }
        if(columnProduct==18) return 3;
        if(columnProduct==2) return 1;
        
        // está na diagonal primária
        if(y==x){
            int diagonalProduct = 1;
            for(int i=0;i<3;i++){
                diagonalProduct *= gameBoard.getPosition(i,i);
            }
            if(diagonalProduct==18) return 3;
            if(diagonalProduct==2) return 1;

        }
        
        // está na diagonal secundária
        if((y==0&&x==2) || (x==0&&y==2) || (y==1&&x==1)){
            int diagonalProduct = 1;
            for(int i=0;i<3;i++){
                diagonalProduct *= gameBoard.getPosition(i,2-i);
            }
            if(diagonalProduct==18) return 3;
            if(diagonalProduct==2) return 1;
        }
        return 0;
    }
    
    public int[] getBestMove(Model gameBoard){
        int[] bestMove = new int[2];
        int lowestScore = 216;

        // loop de menor prioridade, fará a jogada determinada pelo menor score do tabuleiro ou uma jogada defensiva se o oponente irá ganhar
        outerloop2:
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                if(gameBoard.getPosition(i,y)==2){
                    if(isCriticMove(i,y, gameBoard) == 3){
                        bestMove[0] = i;
                        bestMove[1] = y;
                        break outerloop2;
                    }

                    gameBoard.setPosition(i,y,1);
                    if(getScore(gameBoard)<lowestScore){
                        lowestScore = getScore(gameBoard);
                        bestMove[0] = i;
                        bestMove[1] = y;
                    }
                    gameBoard.setPosition(i,y,2);
                }
            }
        }

        // loop de maior prioridade, fará uma jogada que ganhará o jogo se ela existir
        outerloop1:
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                if(gameBoard.getPosition(i,y)==2){
                    if(isCriticMove(i,y, gameBoard) == 1){
                        bestMove[0] = i;
                        bestMove[1] = y;
                        break outerloop1;
                    }
                }
            }
        }
        
        return bestMove;
    }

    // checa se houve empate, quando não há ganhadores e o tabuleiro está cheio
    public boolean checkIfDraw(Model gameBoard){
        boolean fullBoard = true;
        for(int i=0;i<3;i++){
            for(int y=0;y<3;y++){
                if(gameBoard.getPosition(i,y)==2){
                    fullBoard = false;
                }
            }
        }
        
        if(checkIfWinner(gameBoard) != 1 && checkIfWinner(gameBoard) != 2 && checkIfWinner(gameBoard) != 3 && fullBoard){
            return true;
        }
        return false;
    }

    // checa um ganhador quando alguma linha, coluna ou diagonal está totalmente preenchida (ela também retornará 2 caso uma linha, coluna ou diagonal esteja totalmente vazia)
    public int checkIfWinner(Model gameBoard){        
        // linhas
        for(int i=0;i<3;i++){
            if(gameBoard.getPosition(i,0) == gameBoard.getPosition(i,1) && gameBoard.getPosition(i,0) == gameBoard.getPosition(i,2) && gameBoard.getPosition(i,0) != 2)
            return gameBoard.getPosition(i,0);
        }
        //colunas
        for(int i=0;i<3;i++){
            if(gameBoard.getPosition(0,i) == gameBoard.getPosition(1,i) && gameBoard.getPosition(0,i) == gameBoard.getPosition(2,i) && gameBoard.getPosition(0,i) != 2)
            return gameBoard.getPosition(0,i);
        }

        // diagonais
        if(gameBoard.getPosition(0,0) == gameBoard.getPosition(1,1) && gameBoard.getPosition(0,0) == gameBoard.getPosition(2,2) && gameBoard.getPosition(0,0) != 2) return gameBoard.getPosition(0,0);
        if(gameBoard.getPosition(0,2) == gameBoard.getPosition(1,1) && gameBoard.getPosition(0,2) == gameBoard.getPosition(2,0) && gameBoard.getPosition(0,2) != 2) return gameBoard.getPosition(0,2);   
        
        return 0;
    }
}
