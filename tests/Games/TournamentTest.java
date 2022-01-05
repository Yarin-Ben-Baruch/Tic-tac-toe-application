package Games;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    @Test
    void selectionCheck() {
        boolean selectionChose1 = Tournament.selectionCheck("1");
        assertEquals(true,selectionChose1);
        boolean selectionChose2 = Tournament.selectionCheck("2");
        assertEquals(true,selectionChose2);
        boolean selectionChose3 = Tournament.selectionCheck("3");
        assertEquals(true,selectionChose3);
    }

    @Test
    void isDigitsString() {
        boolean isDigit = Tournament.isDigitsString("5");
        assertEquals(true,isDigit);
        boolean isString = Tournament.isDigitsString("TESTESTESTESTESTESTEST");
        assertEquals(false,isString);
    }

    @Test
    void playerTypeSelection() {
        String playerTypeSelectionHuman = Tournament.playerTypeSelection("1");
        assertEquals("Human",playerTypeSelectionHuman);
        String playerTypeSelectionWhatever = Tournament.playerTypeSelection("2");
        assertEquals("Whatever",playerTypeSelectionWhatever);
        String playerTypeSelectionCleverPlayer = Tournament.playerTypeSelection("asd");
        assertEquals("TTT.Players.CleverPlayer",playerTypeSelectionCleverPlayer);
    }

    @Test
    void rendererTypeSelection() {
        String rendererTypeSelectionNone = Tournament.rendererTypeSelection("1");
        String rendererTypeSelectionConsole = Tournament.rendererTypeSelection("2");
        String rendererTypeSelectionGui = Tournament.rendererTypeSelection("3");
        assertEquals("None",rendererTypeSelectionNone);
        assertEquals("Console",rendererTypeSelectionConsole);
        assertEquals("Gui",rendererTypeSelectionGui);
    }
}