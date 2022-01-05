package Players;

import Enums.eMark;

public class PlayerFactory {

    private String name;
    private eMark defaultSign;

    /**
     * A constructor that receives basic player data.
     * @param name player name.
     * @param defaultSign sign(X, O).
     */
    public PlayerFactory(String name, eMark defaultSign) {
        setName(name);
        setDefaultSign(defaultSign);
    }

    /**
     * A constructor that gets a player name, and accordingly creates such an object.
     * @param i_Type class name.
     * @return Player object.
     */
    public Player buildPlayer(String i_Type) {

        switch(i_Type) {
            case "Human":
                return new HumanPlayer(name, defaultSign);
            case "Whatever":
                return new WhateverPlayer(name, defaultSign);
            case "TTT.Players.CleverPlayer":
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
