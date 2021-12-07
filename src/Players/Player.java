package Players;

public abstract class Player {

    private String name;
    private eMark defaultSign;

    //                      name            X || O
    public Player(String i_name, eMark i_DefaultSign) {
        setName(i_name);
        setDefaultSign(i_DefaultSign);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public eMark getDefaultSign() {
        return defaultSign;
    }

    public void setDefaultSign(eMark defaultSign) {
        this.defaultSign = defaultSign;
    }

    abstract void playTurn(Board board, eMark defaultSign, int row, int col);

    abstract int getCoordinates(Board board);

}
