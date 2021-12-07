package Games;

import Enums.eGameStatus;
import Players.Player;
import Renderers.GuiRenderer;
import Renderers.iRenderer;

import javax.swing.*;

public class GameGui extends Game{


    public GameGui(Player i_PlayerX, Player i_PlayerO, iRenderer i_Renderer) {
        super(i_PlayerX, i_PlayerO, i_Renderer);
    }

    @Override
    public eGameStatus run() {

        Board model = new Board();
        ViewModel vm = new ViewModel();
        GuiRenderer view = new GuiRenderer(getPlayerX(), getPlayerO(),model);

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
