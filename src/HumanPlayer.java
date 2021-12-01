import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String i_name, eMark i_defaultSign) {
        super(i_name, i_defaultSign);
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
    public int getCoordinates(Board board){

        int inputRowAndCol;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello " + getDefaultSign() + " please enter coordinates : ");
        inputRowAndCol = scanner.nextInt();

        return inputRowAndCol;
    }

}
