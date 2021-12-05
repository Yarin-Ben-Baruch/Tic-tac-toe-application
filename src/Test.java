public class Test {

    public static void main(String[] args) {

        Game game = new GameGui(new HumanPlayer("X", eMark.X), new HumanPlayer("X", eMark.O), new VoidRenderer());
        game.run();
    }
}
