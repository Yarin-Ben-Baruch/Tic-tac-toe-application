import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel {

    private Board model; // ?
    private TicTacToeApp view;
    private ExecutorService service;

    private int turn;

    ViewModel() {
        this.service = Executors.newFixedThreadPool(3);
    }

    public void getCoordinatesFromGui(int row, int col,eMark mark) {

        service.submit(new Runnable() {
            @Override
            public void run() {
                String message;
                model.putMark(mark, row, col);
                eMark[][]board = model.getBoard();

                if(checkWinner(row, col) != eGameStatus.IN_PROGRESS) {
                    message = mark + " is the winner !!";
                }
                else {
                    message = getOppositeSign(mark) + " turn";
                }

                SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showTurn(message);
                            view.showBoard(board);
                        }
                    });
                }
            }
        );
    }

    public void victory(int row, int col,eMark mark) {

        service.submit(new Runnable() {
                           @Override
                           public void run() {

                               model.putMark(mark, row, col);
                               eMark[][]board = model.getBoard();
                               SwingUtilities.invokeLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       view.showMessage(mark + " is the winner !!");
                                       view.showBoard(board);
                                   }
                               });
                           }
                       }
        );
    }


    public eGameStatus checkWinner(int row, int col)
    {
        return model.GameStatus(row, col);
    }


    public void setModel(Board model) {
        this.model = model;
    }

    public void setView(TicTacToeApp view) {
        this.view = view;
    }

    private eMark getOppositeSign(eMark mark)
    {
        if(mark == eMark.X)
            return eMark.O;
        else
            return eMark.X;
    }
}
