
public interface iPlayer {

    void setDefaultSign(eMark defaultSign);
    eMark getDefaultSign();
    void setName(String name);
    String getName();
    void playTurn(Board board, eMark defaultSign, int row, int col);
    int getCoordinates();

}
