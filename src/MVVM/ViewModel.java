package MVVM;


import Boards.Board;
import Enums.eGameStatus;
import Enums.eMark;
import Renderers.GuiRenderer;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ViewModel {

    //fields.
    private Board model; // ?
    private GuiRenderer view;
    private ExecutorService service;

    /**
     * Initializes a stack of Threads.
     */
    public ViewModel() {
        this.service = Executors.newFixedThreadPool(3);
    }

    /**
     * Gets coordinates from gui and updates the model.
     * Depending on the outcome makes decisions.
     * @param row row number.
     * @param col col number.
     * @param mark sign (X, O).
     */
    public void getCoordinatesFromGui(int row, int col, eMark mark) {

        if(!model.isHaveWinner()) {
            service.submit(new Runnable() {
                               @Override
                               public void run() {
                                   String message;
                                   model.putMark(mark, row, col);
                                   eMark[][] board = model.getBoard();
                                   eGameStatus statusGame = model.gameStatus(row, col);

                                   if (statusGame != eGameStatus.IN_PROGRESS) {
                                       if(statusGame.equals(eGameStatus.DRAW)){
                                           message = "Draw !!";
                                       }
                                       else {
                                           message = mark + " is the winner !!";
                                       }
                                       model.setHaveWinner(true);
                                   } else {
                                       message = model.getOppositeSign(mark) + " turn";
                                   }

                                   SwingUtilities.invokeLater(new Runnable() {
                                       @Override
                                       public void run() {
                                           view.showTurn(message);
                                           view.showBoard(board);
                                           if (model.isHaveWinner()) {
                                               view.disableAll(statusGame, message);
                                           }

                                       }
                                   });
                               }
                           }
            );
        }
    }

    public void setModel(Board model) {
        this.model = model;
    }

    public void setView(GuiRenderer view) {
        this.view = view;
    }

}
