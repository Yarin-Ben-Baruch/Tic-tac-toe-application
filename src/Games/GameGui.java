package Games;

import Boards.Board;
import Enums.eGameStatus;
import MVVM.ViewModel;
import Players.Player;
import Renderers.GuiRenderer;
import Renderers.iRenderer;

import javax.swing.*;

public class GameGui extends Game{

    /**
     * Initializes the data and transfers it to the base class
     * @param i_PlayerX first player.
     * @param i_PlayerO second player.
     * @param i_Renderer board type Gui.
     */
    public GameGui(Player i_PlayerX, Player i_PlayerO, iRenderer i_Renderer) {
        super(i_PlayerX, i_PlayerO, i_Renderer);
    }

    /**
     * Runs the game, GUI-type.
     * @return Returns the type of winner.
     */
    @Override
    public eGameStatus run() {
        int[] emptyArr = new int[3];
        Board model = new Board();
        ViewModel vm = new ViewModel();
        GuiRenderer view = new GuiRenderer(getPlayerX(), getPlayerO(),model, emptyArr);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });

        vm.setModel(model);
        vm.setView(view);
        view.setViewModel(vm);





        eGameStatus winner = eGameStatus.IN_PROGRESS;
        Player[] myPlayers = new Player[2];
        int turnToPlay = 0;
        int inputRowAndCol, row, col;

        myPlayers[0] = getPlayerX();
        myPlayers[1] = getPlayerO();

//        while (winner == eGameStatus.IN_PROGRESS) {
//
//
//        }

        return winner;


//        IModel model = new SimpleInMemoryToDoListModel();
//        IViewModel vm = new SimpleViewModel();
//        IView view = new SimpleToDoListView();

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                view.init();
//                view.start();
//            }
//        });
//
//        vm.setModel(model);
//        vm.setView(view);
//        view.setIViewModel(vm);
    }
}
