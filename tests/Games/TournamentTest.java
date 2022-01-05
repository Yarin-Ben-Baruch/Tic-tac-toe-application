package Games;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    @Test
    void selectionCheck() {
        boolean selectionChose1 = TournamentConsole.selectionCheck("1");
        assertEquals(true,selectionChose1);
        boolean selectionChose2 = TournamentConsole.selectionCheck("2");
        assertEquals(true,selectionChose2);
        boolean selectionChose3 = TournamentConsole.selectionCheck("3");
        assertEquals(true,selectionChose3);
    }

    @Test
    void isDigitsString() {
        boolean isDigit = TournamentConsole.isDigitsString("5");
        assertEquals(true,isDigit);
        boolean isString = TournamentConsole.isDigitsString("TESTESTESTESTESTESTEST");
        assertEquals(false,isString);
    }

    @Test
    void playerTypeSelection() {
        String playerTypeSelectionHuman = TournamentConsole.playerTypeSelection("1");
        assertEquals("Human",playerTypeSelectionHuman);
        String playerTypeSelectionWhatever = TournamentConsole.playerTypeSelection("2");
        assertEquals("Whatever",playerTypeSelectionWhatever);
        String playerTypeSelectionCleverPlayer = TournamentConsole.playerTypeSelection("asd");
        assertEquals("TTT.Players.CleverPlayer",playerTypeSelectionCleverPlayer);
    }

    @Test
    void rendererTypeSelection() {
        String rendererTypeSelectionNone = TournamentConsole.rendererTypeSelection("1");
        String rendererTypeSelectionConsole = TournamentConsole.rendererTypeSelection("2");
        String rendererTypeSelectionGui = TournamentConsole.rendererTypeSelection("3");
        assertEquals("None",rendererTypeSelectionNone);
        assertEquals("Console",rendererTypeSelectionConsole);
        assertEquals("Gui",rendererTypeSelectionGui);
    }
}