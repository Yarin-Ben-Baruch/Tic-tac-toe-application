import java.util.ArrayList;

public class Board {

    // משתנים של המחלקה
    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;
    private int emptyPlace;
    // משתנה מסוג enum שממלא את הלוח בהתאם לסימן
    public eMark[][] board;

    public eMark[][] getBoard() {
        return board;
    }

    // מערך ששומר את המקומות הריקים בלוח
    public static ArrayList<Integer> emptyLocations;

    /*
    יוצרים לוח בגודל שנבחר(size)
    מעדכנים את רשימת המקומות הריקים + מאתחלים את הלוח
     */
    public Board()
    {
        board = new eMark[SIZE][SIZE];
        emptyLocations = new ArrayList<>();
        installBoard();
        emptyPlace = SIZE*SIZE;
    }

    /*
    מטודה שמאתחלת את כל הלוח למקומות ריקים
    בנוסף מאתחל מערך השומר את כל המיקומים של המקומות הריקים
     */
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

    /*
    מטודה שמקבלת מיקום בלוח וסימן.
    המטודה בודקת את תיקונת הקלט, במידה והוא תקין מחזיר true
    וממלא בלוח את הסימן + מורידה את המיקום שלו מהמערך השומר את המקומות
     */
    public boolean putMark(eMark mark, int row, int col) // סימון על הלוח
    {
        Boolean result  = false;
        int numberToRemove;
        if(row > 0 && row <= SIZE && col > 0 && col <= SIZE )
        {
            if(board[row - 1][col - 1] == eMark.BLANK) {
                board[row - 1][col - 1] = mark;
                result = true;
                emptyPlace--;
                numberToRemove = row * 10 + col;
                emptyLocations.removeIf(number -> number == numberToRemove);
            }
        }
        return result;
    }

    /*
    מטודה שבודקת האם יש הכרעה במשחק .
    אחריי שהשחקן שתורו מבצע מהלך, הפונקציה נקראת ובודק האם המהלך שהשחקן עשה גרם לביצוע סטרייק בגודל שנבחר .
    נבדק פה כל סוגי האפשרויות לסטרייק .
    במידה ויש סטרייק מחזירים אמת
    במידה ואין מחזירים שקר .
     */
    public eGameStatus GameStatus(int row, int col) // במהלך המשחק / מי ניצח
    {
        eMark mark = board[row-1][col-1];
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

    /*
    פונקצית עזר לGameStatus
    היא מקבלת את המיקום של המהלך שהוכנס .
    בנוסף היא מקבלת כיוון של עמודה ושורה.
    בעזרת הכיוון היא בודקת כמה מאותו מהלך שהוכנס נמצא ברצף עם הכיוון שהיא קיבלה .
    נמצאים פה תנאי קצה כמו למשל שלא תבדוק אחריי גודל הלוח
    המטודה מחזירה בסופה את גודל הרצף שהיא הלכה לכיוון שלו
     */
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

    // מטודה שמחזירה סימן של מיקום בלוח
    public eMark getMark(int row, int col)
    {
        return board[row][col];
    }


}
