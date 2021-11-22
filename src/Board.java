import java.util.ArrayList;

public class Board {
    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;
    private int emptyPlace;
    private eMark[][] board;
    public static ArrayList<Integer> emptyLocations;

    public Board()
    {
        board = new eMark[SIZE][SIZE];
        emptyLocations = new ArrayList<>();
        installBoard();
        emptyPlace = SIZE*SIZE;
    }

    private void installBoard(){
        int num = 0;

        for(int i = 0 ; i < board.length ; i++)
        {
            for (int j = 0 ; j < board[i].length ; j++)
            {
                num = (i+1) * 10 + (j+1);
                board[i][j] = eMark.BLANK;
                emptyLocations.add(num);
            }
        }

    }

    public boolean putMark(eMark mark, int row, int col) // סימון על הלוח
    {
        Boolean result  = false;
        int numberToRemove;
        if(row > 0 && row <= SIZE && col > 0 && col <= SIZE )
        {
            if(board[row-1][col-1] == eMark.BLANK) {
                board[row - 1][col - 1] = mark;
                result = true;
                emptyPlace--;
                numberToRemove = row*10 + col;
                emptyLocations.removeIf(number -> number == numberToRemove);
            }
        }
        return result;
    }

    public eGameStatus GameStatus(int row, int col, eMark mark) // במהלך המשחק / מי ניצח
    {
        eGameStatus result = eGameStatus.IN_PROGRESS;
        boolean flag = false;
        int SequenceOfColumn = checkingDirection(row-1, col-1, mark, 1 ,0)
                + checkingDirection(row-1, col-1, mark, -1 ,0);
        int SequenceOfLine = checkingDirection(row-1, col-1, mark, 0 ,-1)
                + checkingDirection(row-1, col-1, mark, 0 ,1);
        int SequenceOfMainDiagonal = checkingDirection(row-1, col-1, mark, -1 ,-1)
                + checkingDirection(row-1, col-1, mark, 1 ,1);
        int SequenceOfSecondaryDiagonal = checkingDirection(row-1, col-1, mark, -1 ,1)
                + checkingDirection(row-1, col-1, mark, 1 ,-1);

        if (emptyPlace == 0) {
            return eGameStatus.DRAW;
        }

        if(SequenceOfLine == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfColumn == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfMainDiagonal == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfSecondaryDiagonal == WIN_STREAK - 1)
            flag = true;

        if(flag == true)
        {
            if(mark == eMark.X)
                result = eGameStatus.X_WIN;
            if (mark == eMark.O)
                result = eGameStatus.O_WIN;
        }

        return result;
    }

    private int checkingDirection(int row, int col, eMark mark,int moveCol, int moveRow)
    {
        int count = 0;

        row += moveRow;
        col += moveCol;

        while(row < SIZE && col < SIZE && row >= 0 && col >= 0 && board[row][col] == mark)
        {
            count++;
            row += moveRow;
            col += moveCol;

        }

        return count;
    }

    public eMark getMark(int row, int col)
    {
        return board[row][col];
    }


}
