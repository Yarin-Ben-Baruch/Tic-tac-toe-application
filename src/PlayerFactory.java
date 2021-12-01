public class PlayerFactory {

    private String name;
    private eMark defuletSigan;

    public PlayerFactory(String i_name, eMark i_defuletSigan) {
        setName(i_name);
        setDefuletSigan(i_defuletSigan);
    }

    public Player buildPlayer(String i_Type) {

        switch(i_Type) {
            case "Human":
                return new HumanPlayer(name, defuletSigan);
            case "Whatever":
                return new WhateverPlayer(name, defuletSigan);
            case "CleverPlayer":
                return new CleverPlayer(name, defuletSigan);
            default:
                return null;
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefuletSigan(eMark defuletSigan) {
        this.defuletSigan = defuletSigan;
    }

}
