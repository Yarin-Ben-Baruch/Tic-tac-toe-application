package Games;

import Enums.eGameStatus;
import Enums.eMark;
import Players.Player;
import Players.PlayerFactory;
import Renderers.RendererFactory;
import Renderers.iRenderer;

import java.util.Arrays;
import java.util.Scanner;

public class Tournament {

    private final Player player1;
    private final Player player2;
    private final int numberRounds;
    private final iRenderer renderer;

    public Tournament(Player player1, Player player2, int numberRounds, iRenderer renderer) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberRounds = numberRounds;
        this.renderer = renderer;
    }

    public int[] playTournament() {

        int[] results = new int[3];
        Arrays.fill(results,0);

        Player[] players = new Player[2];
        int randomPlayerOne,randomPlayerTwo;
        players[0] = player1;
        players[1] = player2;

        for(int i = 0 ; i < numberRounds ; i++)
        {
            System.out.println("Start game number " + (i+1) + "/" + numberRounds);
            randomPlayerOne = i % 2;
            randomPlayerTwo = (randomPlayerOne + 1) % 2;

//            if(renderer instanceof TTT.Renderers.TicTacToeApp)
//                TTT.Games.Game game = new TTT.Games.GameGui(players[randomPlayerOne],players[randomPlayerTwo], renderer);
//            else
            Game game = new GameConsole(players[randomPlayerOne],players[randomPlayerTwo], renderer);

            eGameStatus winner = game.run();

            if(winner == eGameStatus.DRAW){
                results[2]++;
            }

            if(winner == eGameStatus.X_WIN)
            {
                results[0]++;
            }

            if(winner == eGameStatus.O_WIN)
            {
                results[1]++;
            }
        }

        return results;
}

    public static void userMenu() {
        System.out.println("Welcome to TIC TAC TOE game!!!\n lets start play...");
        String playerType1, playerType2, selection, rendererType, numberOfRoundsStr = null;
        int numberOfRounds;
        int[] result;
        System.out.println("Chose option for player 1 by index: \n1.human  \n2.computer \n3.expert computer");
        Scanner scanner = new Scanner(System.in);
        selection = scanner.nextLine();

        while (!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        playerType1 = playerTypeSelection(selection);

        System.out.println("Chose option for player 2 by index: \n1.human  \n2.computer \n3.expert computer");
        selection = scanner.nextLine();
        while (!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        playerType2 = playerTypeSelection(selection);

        System.out.println("Chose option for renderer type by index: \n1.none(without board) \n2.console \n3.GUI");
        selection = scanner.nextLine();
        while(!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        rendererType = rendererTypeSelection(selection);

        System.out.println("Chose the number of games you want to play:");

        boolean flag = false;
        while(!flag)
        {
            numberOfRoundsStr = scanner.nextLine();
            flag = isDigitsString(numberOfRoundsStr);
            if(!flag) {
                System.out.println("Wrong selection please try again");
            }
        }
        numberOfRounds = Integer.parseInt(numberOfRoundsStr);


        iRenderer renderer = new RendererFactory().buildRenderer(rendererType);
        Player playerX = new PlayerFactory("X", eMark.X).buildPlayer(playerType1);
        Player playerO = new PlayerFactory("O",eMark.O).buildPlayer(playerType2);
        Tournament tournament = new Tournament(playerX,playerO,numberOfRounds, renderer);
        result = tournament.playTournament();

        System.out.println("TTT.Players.Player X win : " + result[0]);
        System.out.println("TTT.Players.Player O win : " + result[1]);
        System.out.println("Total draw : " + result[2]);

    }

    public static boolean selectionCheck(String str) {
        return str.equals("1") || str.equals("2") || str.equals("3");
    }

    public static boolean isDigitsString(String str) {

        for (char currentChar : str.toCharArray() ) {
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public static String playerTypeSelection (String str) {
        String playerType;
        if (str.equals("1")) {
            playerType = "Human";
        }else if (str.equals("2")) {
            playerType = "Whatever";
        }else {
            playerType = "TTT.Players.CleverPlayer";
        }
        return playerType;
    }

    public static String rendererTypeSelection (String str) {

        String playerType;
        if (str.equals("1")) {
            playerType = "None";
        }else if (str.equals("2")) {
            playerType = "Console";
        }else {
            playerType = "Gui";
        }
        return playerType;
    }

    public static void main(String[] args) {

        userMenu();
    }
}
