package Games;


import Boards.Board;
import Enums.eGameStatus;
import Players.Player;
import Renderers.iRenderer;

/**
 * A class that implements the console board type.
 */
public class GameConsole extends Game{

    /**
     * Initializes the data and transfers it to the base class
     * @param i_PlayerX first player.
     * @param i_PlayerO second player.
     * @param i_Renderer board type console.
     */
    public GameConsole(Player i_PlayerX, Player i_PlayerO, iRenderer i_Renderer) {
        super(i_PlayerX, i_PlayerO, i_Renderer);
    }

    /**
     * Runs the game, console-type.
     * @return Returns the type of winner.
     */
    @Override
    public eGameStatus run() {
        Board board = new Board();
        eGameStatus winner = eGameStatus.IN_PROGRESS;
        Player[] myPlayers = new Player[2];
        int turnToPlay = 0;
        int inputRowAndCol, row, col;

        myPlayers[0] = getPlayerX();
        myPlayers[1] = getPlayerO();

        while (winner == eGameStatus.IN_PROGRESS) {

            turnToPlay++;

            inputRowAndCol = myPlayers[turnToPlay % 2].getCoordinates(board);

            col = inputRowAndCol % 10;
            row = inputRowAndCol / 10;

            myPlayers[turnToPlay % 2].playTurn(board, myPlayers[turnToPlay % 2].getDefaultSign(), row, col);

            getRenderer().renderBoard(board);
            //winner = board.GameStatus(row, col, myPlayers[turnToPlay % 2].getDefaultSign());
            winner = board.GameStatus(row, col);

        }

        return winner;
    }

}
