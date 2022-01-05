package Players;

import MVVM.Board;
import Enums.eMark;
public class WhateverPlayer extends Player {

    /**
     * A constructor that initializes the player data.
     * @param name name player(PlayerX, PlayerO).
     * @param defaultSign what his sign(X, O).
     */
    public WhateverPlayer(String name, eMark defaultSign) {
        super(name, defaultSign);
    }

    /**
     * A method that represents a player's move.
     * @param board On which board to mark.
     * @param defaultSign What a sign to put in.
     * @param row row number.
     * @param col col number.
     */
    @Override
    public void playTurn(Board board, eMark defaultSign, int row, int col) {

        board.putMark(defaultSign,row,col);
    }

    /**
     * A method that accepts coordinates that the player selects.
     * @param board On which board to mark.
     * @return Array location.
     */
    @Override
    public int getCoordinates(Board board){

        int index = (int)(Math.random() * Board.emptyLocations.size());

        return Board.emptyLocations.get(index);
    }
}
