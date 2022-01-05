package Players;

import Boards.Board;
import Enums.eMark;
public abstract class Player {

    //fields.
    private String name;
    private eMark defaultSign;

    /**
     * A constructor that initializes the player data.
     * @param name name player(PlayerX, PlayerO).
     * @param defaultSign what his sign(X, O).
     */
    public Player(String name, eMark defaultSign) {
        setName(name);
        setDefaultSign(defaultSign);
    }

    /**
     * return the player name.
     * @return name player.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method that returns the player's mark.
     * @return player mark.
     */
    public eMark getDefaultSign() {
        return defaultSign;
    }

    public void setDefaultSign(eMark defaultSign) {
        this.defaultSign = defaultSign;
    }

    /**
     * A method that represents a player's move.
     * @param board On which board to mark.
     * @param defaultSign What a sign to put in.
     * @param row row number.
     * @param col col number.
     */
    public abstract void playTurn(Board board, eMark defaultSign, int row, int col);

    /**
     * A method that accepts coordinates that the player selects.
     * @param board On which board to mark.
     * @return Array location.
     */
    public abstract int getCoordinates(Board board);
}
