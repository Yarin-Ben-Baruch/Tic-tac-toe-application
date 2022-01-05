package Games;

import Enums.eGameStatus;
import Players.Player;
import Renderers.iRenderer;


public abstract class Game {

    //fields
    private Player playerX;
    private Player playerO;
    private iRenderer renderer;

    /**
     * Initializes the player types and board type.
     * @param i_PlayerX first player.
     * @param i_PlayerO second player.
     * @param i_Renderer board type (Console, Gui).
     */
    public Game(Player i_PlayerX, Player i_PlayerO, iRenderer i_Renderer) {
        setPlayerO(i_PlayerO);
        setPlayerX(i_PlayerX);
        setRenderer(i_Renderer);
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public iRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(iRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * A method that runs the game on each of the boards
     * @return Returns the type of winner
     */
    abstract public eGameStatus run();
}


