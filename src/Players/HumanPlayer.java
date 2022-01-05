package Players;

import MVVM.Board;
import Enums.eMark;

import java.util.Scanner;

public class HumanPlayer extends Player {

    /**
     * A constructor that initializes the player data and passes it to the base class.
     * @param name name player(PlayerX, PlayerO).
     * @param defaultSign what his sign(X, O).
     */
    public HumanPlayer(String name, eMark defaultSign) {
        super(name, defaultSign);
    }

    /**
     * A method that represents a player's move.
     * @param board On which board to mark.
     * @param row row number.
     * @param col col number.
     */
    @Override
    public void playTurn(Board board, eMark mark , int row, int col) {

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

    /**
     * A method that accepts coordinates that the player selects.
     * @param board On which board to mark.
     * @return Array location.
     */
    @Override
    public int getCoordinates(Board board){
        int inputRowAndCol;
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello " + getDefaultSign() + " please enter coordinates : ");
        inputRowAndCol = scanner.nextInt();


        return inputRowAndCol;
    }

}
