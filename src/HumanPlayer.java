import java.util.Scanner;

public class HumanPlayer implements iPlayer{

    private String name;
    private eMark defuletSigan;

    public HumanPlayer(String i_name, eMark i_defuletSigan) {
        setName(i_name);
        setDefuletSigan(i_defuletSigan);
    }

    @Override
    public eMark getDefuletSigan() {
        return defuletSigan;
    }

    @Override
    public void setDefuletSigan(eMark defuletSigan) {
        this.defuletSigan = defuletSigan;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void playTurn(Board board, eMark mark , int row, int col)
    {

        Scanner scanner = new Scanner(System.in);
        Boolean answer = board.putMark(mark,row,col);

        while (!answer) {
            if (!answer) {
                System.out.println("Wrong coordinates , please try again");
            }

            int newRow = scanner.nextInt();
            int newCol = newRow % 10;
            newRow = newRow / 10;
            answer = board.putMark(mark, newRow, newCol);
        }
    }

    @Override
    public int getCoordinates(){

        int inputRowAndCol;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello "+ getDefuletSigan()+" please enter coordinates : ");
        inputRowAndCol = scanner.nextInt();

        return inputRowAndCol;
    }

}
