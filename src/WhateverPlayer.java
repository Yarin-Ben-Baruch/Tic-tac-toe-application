import java.util.Scanner;

public class WhateverPlayer implements iPlayer {

    private String name;
    private eMark defuletSigan;

    public WhateverPlayer(String name, eMark defuletSigan) {
        setName(name);
        setDefuletSigan(defuletSigan);
    }

    @Override
    public void setDefuletSigan(eMark defuletSigan) {
        this.defuletSigan = defuletSigan;
    }

    @Override
    public eMark getDefuletSigan() {
        return defuletSigan;
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
    public void playTurn(Board board, eMark defuletSigan, int row, int col) {

        board.putMark(defuletSigan,row,col);
    }

    public int randLocation(){

        int index = (int)(Math.random() * Board.emptyLocations.size());

        return Board.emptyLocations.get(index);
    }

}
