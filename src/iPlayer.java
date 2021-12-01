
public interface iPlayer {

    void setDefuletSigan(eMark defuletSigan);
    eMark getDefuletSigan();
    void setName(String name);
    String getName();
    void playTurn(Board board, eMark defuletSigan, int row, int col);
    int getCoordinates();

}
