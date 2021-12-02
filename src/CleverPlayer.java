import java.util.HashMap;

public class CleverPlayer extends WhateverPlayer {

    private HashMap<eGameStatus, Integer> scores;

    public CleverPlayer(String name, eMark defaultSign) {
        super(name, defaultSign);

        scores = new HashMap<>();

        if(defaultSign == eMark.X) {
            scores.put(eGameStatus.X_WIN, 1);
            scores.put(eGameStatus.O_WIN, -1);
            scores.put(eGameStatus.DRAW, 0);
        }
        else
        {
            scores.put(eGameStatus.X_WIN, -1);
            scores.put(eGameStatus.O_WIN, 1);
            scores.put(eGameStatus.DRAW, 0);
        }
    }

    private eMark getOppositeSign()
    {
        if(getDefaultSign() == eMark.X)
            return eMark.O;
        else
            return eMark.X;
    }

    @Override
    public int getCoordinates(Board board){

        int bestScore = Integer.MIN_VALUE;
        int[] move = {0, 0};
        eMark[][] currentBoard;

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                currentBoard = board.getBoard();
                // Is the spot available?
                if (currentBoard[i][j] == eMark.BLANK) {
                    currentBoard[i][j] = getDefaultSign();
                    int score = minimax(currentBoard, 0, false);
                    currentBoard[i][j] = eMark.BLANK;
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i + 1;
                        move[1] = j + 1;
                    }
                }
            }
        }

        return move[0] * 10 + move[1];
    }

    int minimax(eMark[][] board, int depth, boolean isMaximizing) {

        eGameStatus result = checkWinner(board);

        if (result != eGameStatus.IN_PROGRESS) {
            return scores.get(result);
        }

        int bestScore;

        if(isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIZE ; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    // Is the spot available?
                    if (board[i][j] == eMark.BLANK) {
                        board[i][j] = getDefaultSign();
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = eMark.BLANK;
                        bestScore = Math.max(score, bestScore);

                    }
                }
            }
        }
        else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    // Is the spot available?
                    if (board[i][j] == eMark.BLANK) {
                        board[i][j] = getOppositeSign();
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = eMark.BLANK;
//                        System.out.println(getOppositeSign());
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    boolean equals3(eMark a, eMark b, eMark c) {
        return a == b && b == c && a != eMark.BLANK;
    }

    public eGameStatus checkWinner(eMark[][] board) {
        // Must use 'n' for "null" since Java doesn't allow primitive data types to be null
        eMark winner = null;



        // horizontal
        for (int i = 0; i < 3; i++) {
            if (equals3(board[i][0], board[i][1], board[i][2])) {
                winner = board[i][0];
            }
        }

        // Vertical
        for (int i = 0; i < 3; i++) {
            if (equals3(board[0][i], board[1][i], board[2][i])) {
                winner = board[0][i];
            }
        }

        // Diagonal
        if (equals3(board[0][0], board[1][1], board[2][2])) {
            winner = board[0][0];
        }
        if (equals3(board[2][0], board[1][1], board[0][2])) {
            winner = board[2][0];
        }

        int openSpots = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == eMark.BLANK) {
                    openSpots++;
                }
            }
        }
//                         ||
        if (winner == null || openSpots == 0) {
            return eGameStatus.IN_PROGRESS;
        } else {
            if(winner == eMark.X)
                return eGameStatus.X_WIN;
            else
                return eGameStatus.O_WIN;
        }
    }

}


