public interface iPlayer {

    void setDefuletSigan(eMark defuletSigan);
    eMark getDefuletSigan();
    void setName(String name);
    void playTurn(Board board, eMark defuletSigan, int row, int col);
    String getName();
}
