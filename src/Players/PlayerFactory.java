package Players;

import Enums.*;


public class PlayerFactory {

    private String name;
    private eMark defaultSign;

    public PlayerFactory(String i_name, eMark i_defaultSign) {
        setName(i_name);
        setDefaultSign(i_defaultSign);
    }

    public Player buildPlayer(String i_Type) {

        switch(i_Type) {
            case "Human":
                return new HumanPlayer(name, defaultSign);
            case "Whatever":
                return new WhateverPlayer(name, defaultSign);
            case "Players.CleverPlayer":
                return new CleverPlayer(name, defaultSign);
            default:
                return null;
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultSign(eMark defaultSign) {
        this.defaultSign = defaultSign;
    }

}
