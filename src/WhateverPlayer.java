import java.util.Scanner;

public class WhateverPlayer implements iPlayer {

    private String name;
    private eMark defaultSign;

    //                      name                X || O
    public WhateverPlayer(String name, eMark defaultSign) {
        setName(name);
        setDefaultSign(defaultSign);
    }

    @Override
    public void setDefaultSign(eMark defaultSign) {
        this.defaultSign = defaultSign;
    }

    @Override
    public eMark getDefaultSign() {
        return defaultSign;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public void playTurn(Board board, eMark defaultSign, int row, int col) {

        board.putMark(defaultSign,row,col);
    }

    @Override
    public int getCoordinates(){

        int index = (int)(Math.random() * Board.emptyLocations.size());

        return Board.emptyLocations.get(index);
    }

}
