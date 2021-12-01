import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Tournament {

    private iPlayer player1;
    private iPlayer player2;
    private int numberRounds;
    private iRenderer renderer;

    public Tournament(iPlayer player1, iPlayer player2, int numberRounds, iRenderer renderer) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberRounds = numberRounds;
        this.renderer = renderer;
    }

    public int[] playTournament() {

        int[] results = new int[3];
        Arrays.fill(results,0);

        iPlayer[] players = new iPlayer[2];
        int randomPlayerOne,randomPlayerTwo;
        players[0] = player1;
        players[1] = player2;

        for(int i = 0 ; i < numberRounds ; i++)
        {
            System.out.println("Start game number " + (i+1) + "/" + numberRounds);
            randomPlayerOne = i % 2;
            randomPlayerTwo = (randomPlayerOne + 1) % 2;

            Game game = new Game(players[randomPlayerOne],players[randomPlayerTwo], renderer);
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

    public static void main(String[] args) {

        int[] result;

        iRenderer renderer = new RendererFactory().buildRenderer("Console");
        iRenderer rendererNone = new RendererFactory().buildRenderer("None");

        iPlayer playerX = new PlayerFactory("X",eMark.X).buildPlayer("Human");
        iPlayer playerO = new PlayerFactory("O",eMark.O).buildPlayer("Human");

        iPlayer playerWhat = new PlayerFactory("O",eMark.O).buildPlayer("Whatever");
        iPlayer playerWhat2 = new PlayerFactory("O",eMark.X).buildPlayer("Whatever");


//        Tournament tournament = new Tournament(playerWhat2,playerWhat,100, rendererNone);
        Tournament tournament = new Tournament(playerX, playerO,1, renderer);

        result = tournament.playTournament();

        System.out.println("Player X win : " + result[0]);
        System.out.println("Player O win : " + result[1]);
        System.out.println("Total draw : " + result[2]);

    }

    public void UserMenu() {
        System.out.println("Welcome to TIC TAC TOE game!!!\n lets start play...");
        String playerType1, playerType2, selection, rendererType, numberOfRoundsStr;
        int numberOfRounds;
        System.out.println("Chose option for player 1 by index: \n1.human  \n2.computer / \n3.expert computer");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 1; i++) {
            selection = scanner.nextLine();
            if (!selectionCheck(selection))
            {
                System.out.println("Wrong selection please try again");
                i--;
                continue;
            }
            playerType1 = playerTypeSelection(selection);
        }

        System.out.println("Chose option for player 2 by index: \n1.human  \n2.computer / \n3.expert computer");
        for (int i = 0; i < 1; i++) {
            selection = scanner.nextLine();
            if (!selectionCheck(selection))
            {
                System.out.println("Wrong selection please try again");
                i--;
                continue;
            }
            playerType2 = playerTypeSelection(selection);
        }

        System.out.println("Chose option for renderer type by index: \n1.none(without board)  \n2.console / \n3.GUI");
        for (int i = 0; i < 1; i++) {
            selection = scanner.nextLine();
            if (!selectionCheck(selection))
            {
                System.out.println("Wrong selection please try again");
                i--;
                continue;
            }
            rendererType = rendererTypeSelection(selection);
        }

        System.out.println("Chose the number of games you want to play:");
        for (int i = 0; i < 1; i++) {
            numberOfRoundsStr = scanner.nextLine();
            if (!isDigitsString(numberOfRoundsStr)) {
                System.out.println("Wrong selection please try again");
                i--;
                continue;
            }
            numberOfRounds = Integer.parseInt(numberOfRoundsStr);
        }



    }

    public boolean selectionCheck(String str) {
        return str == "1" || str == "2" || str == "3";
    }

    public boolean isDigitsString(String str) {

        for (char currentChar : str.toCharArray() ) {
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public String playerTypeSelection (String str) {
        String playerType = null;
        if (str == "1") {
            playerType = "Human";
        }else if ( str == "2") {
            playerType = "Whatever";
        }else {
            playerType = "Expert";
        }
        return playerType;
    }

    public String rendererTypeSelection (String str) {
        String playerType = null;
        if (str == "1") {
            playerType = "None";
        }else if ( str == "2") {
            playerType = "Console";
        }else {
            playerType = "Gui";
        }
        return playerType;
    }


}
