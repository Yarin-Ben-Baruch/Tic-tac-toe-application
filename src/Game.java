import java.util.Scanner;

public class Game {

    private iPlayer playerX;
    private iPlayer playerO;
    private iRenderer renderer;

    /*
   מאתחל את סוגי השחקנים וסוג הלוח
     */
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
        Board board = new Board();
        eGameStatus winner = eGameStatus.IN_PROGRESS;
        iPlayer[] myPlayers = new iPlayer[2];
        int turnToPlay = 0;
        int inputRowAndCol, row, col;

        myPlayers[0] = playerX;
        myPlayers[1] = playerO;

        while (winner == eGameStatus.IN_PROGRESS) {

            inputRowAndCol = myPlayers[turnToPlay % 2].getCoordinates();

            col = inputRowAndCol % 10;
            row = inputRowAndCol / 10;

            myPlayers[turnToPlay % 2].playTurn(board,myPlayers[turnToPlay % 2].getDefuletSigan(), row, col);

            renderer.renderBoard(board);
            winner = board.GameStatus(row, col, myPlayers[turnToPlay % 2].getDefuletSigan());
            turnToPlay++;
        }

        return winner;
    }

}
