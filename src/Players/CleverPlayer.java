package Players;

import Boards.Board;
import Enums.eGameStatus;
import Enums.eMark;

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

    private eMark getOppositeSign() {
        if(getDefaultSign() == eMark.X)
            return eMark.O;
        else
            return eMark.X;
    }

    @Override
    public int getCoordinates(Board board){

        int bestScore = Integer.MIN_VALUE;
        int[] move = {0, 0};
        eMark[][] currentBoard = board.getBoard();;

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
//                currentBoard = board.getBoard();
                // Is the spot available?
                if (currentBoard[i][j] == eMark.BLANK) {
                    currentBoard[i][j] = getDefaultSign();
                    int score = minimax(currentBoard, 0, false, board , i+1, j+1);
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

    int minimax(eMark[][] board, int depth, boolean isMaximizing, Board boardClass, int currentI, int currentJ) {

        //eGameStatus result = checkWinner(board);
        eGameStatus result = boardClass.GameStatus(currentI,currentJ);

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
                        int score = minimax(board, depth + 1, false, boardClass, i+1, j+1);
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
                        int score = minimax(board, depth + 1, true, boardClass, i+1, j+1);
                        board[i][j] = eMark.BLANK;
//                        System.out.println(getOppositeSign());
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }


}


