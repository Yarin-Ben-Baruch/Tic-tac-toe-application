
public abstract class Game {

    private Player playerX;
    private Player playerO;
    private iRenderer renderer;

    /*
   מאתחל את סוגי השחקנים וסוג הלוח
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

    abstract public eGameStatus run();
}


