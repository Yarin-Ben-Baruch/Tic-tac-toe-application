package Players;

import Boards.Board;
import Enums.eMark;
public class WhateverPlayer extends Player {

    //                      name                X || O
    public WhateverPlayer(String name, eMark defaultSign) {
        super(name, defaultSign);
    }

    @Override
    public void playTurn(Board board, eMark defaultSign, int row, int col) {

        board.putMark(defaultSign,row,col);
    }

    @Override
    public int getCoordinates(Board board){

        int index = (int)(Math.random() * Board.emptyLocations.size());

        return Board.emptyLocations.get(index);
    }

}
