import java.lang.reflect.Array;
import java.util.Arrays;

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

            Game game = new Game(players[randomPlayerOne],players[randomPlayerTwo],renderer);
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

        iRenderer renderer = new RendererFactory().buildRenderer("Console");
        iRenderer rendererNone = new RendererFactory().buildRenderer("None");

        iPlayer playerX = new PlayerFactory("X",eMark.X).buildPlayer("Human");
        iPlayer playerO = new PlayerFactory("O",eMark.O).buildPlayer("Human");

        iPlayer playerWhat = new PlayerFactory("O",eMark.O).buildPlayer("Whatever");
        iPlayer playerWhat2 = new PlayerFactory("O",eMark.X).buildPlayer("Whatever");

        int[] result;

        Tournament tournament = new Tournament(playerWhat2,playerWhat,10,rendererNone);
        result = tournament.playTournament();

        System.out.println("Player X win : " + result[0]);
        System.out.println("Player O win : " + result[1]);
        System.out.println("Total draw : " + result[2]);
    }

}
