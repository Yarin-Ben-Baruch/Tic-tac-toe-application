import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel {

    private Board model; // ?
    private TicTacToeApp view;
    private ExecutorService service;

    ViewModel() {
        this.service = Executors.newFixedThreadPool(3);
    }


    public void getCoordinatesFromGui(int row, int col) {
        service.submit(new Runnable() {
            @Override
            public void run() {

                eMark sign = eMark.BLANK;//
                model.putMark(sign, row, col);
                eMark[][]board = model.getBoard();
                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showTurn(sign +" turn");
                            view.showBoard(board);
                        }
                    });
                }
            }
        );
    }


}
