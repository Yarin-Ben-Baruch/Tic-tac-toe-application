import java.util.Scanner;

public class Game {

    private iPlayer playerX;
    private iPlayer playerO;
    private iRenderer renderer;

    public Game(iPlayer i_PlayerX, iPlayer i_PlayerO, iRenderer i_Renderer) {
        setPlayerO(i_PlayerO);
        setPlayerX(i_PlayerX);
        setRenderer(i_Renderer);
    }

    public void setPlayerX(iPlayer playerX) {
        this.playerX = playerX;
    }

    public void setPlayerO(iPlayer playerO) {
        this.playerO = playerO;
    }

    public void setRenderer(iRenderer renderer) {
        this.renderer = renderer;
    }

    public eGameStatus run()
    {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        eGameStatus winner = eGameStatus.IN_PROGRESS;
        iPlayer[] myPlayers = new iPlayer[2];
        int turnToPlay = 0;
        int row;
        int col;

        myPlayers[0] = playerX;
        myPlayers[1] = playerO;

        while (winner == eGameStatus.IN_PROGRESS) {

            if(myPlayers[turnToPlay % 2] instanceof WhateverPlayer cuurentPlayer)
            {
                //System.out.print("What ever player :");
                row = cuurentPlayer.randLocation();
            }
            else {
                System.out.print("Player " + myPlayers[turnToPlay % 2].getName() + " type coordinates : ");
                row = scanner.nextInt();
            }
            col = row % 10;
            row = row / 10;

            myPlayers[turnToPlay % 2].playTurn(board,myPlayers[turnToPlay % 2].getDefuletSigan() , row, col);
            renderer.renderBoard(board);
            winner = board.GameStatus(row, col, myPlayers[turnToPlay % 2].getDefuletSigan());
            turnToPlay++;
        }

        return winner;
    }

}
