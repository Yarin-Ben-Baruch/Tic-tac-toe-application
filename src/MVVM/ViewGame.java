package MVVM;

import Enums.eMark;
import Games.Game;
import Games.GameGui;
import Games.Tournament;
import Players.CleverPlayer;
import Players.HumanPlayer;
import Players.WhateverPlayer;
import Renderers.VoidRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ViewGame extends JFrame {

    private JFrame startFrame = new JFrame("Game options");
    private JPanel playersPanel = new JPanel();
    private JPanel boardSizePanel = new JPanel();
    private JButton[] boardSizeSelectionButtons = new JButton[3];
    private JButton[] participantsSelectionButtons = new JButton[2];

    private JPanel buttonPanel = new JPanel();
    private Tournament tournament;

    public ViewGame() {
        setStartFrame();
    }

    public void setStartFrame() {

        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(500, 250);
        startFrame.setResizable(true);
        startFrame.setLocationRelativeTo(null);
        try {
            startFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/backGroundForMenu.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        startFrame.setLayout(null);

        String[] titleForButtons = {"Player vs Player", "Player vs CPU"};
        int x = 50;
        for (int i = 0; i < 2; i++) {

            participantsSelectionButtons[i] = new JButton();
            participantsSelectionButtons[i].setFocusable(false);
            participantsSelectionButtons[i].setText(titleForButtons[i]);
            participantsSelectionButtons[i].setBackground(new Color(0xFFFFFF));
            participantsSelectionButtons[i].setBounds(x, 30, 135, 50);
            x += 145;
            startFrame.add(participantsSelectionButtons[i]);
        }

        x = 50;
        String[] levels = {"Easy", "Hard"};
        JComboBox levelComboBox = new JComboBox(levels);
        levelComboBox.setBounds(x, 100, 135, 50);
        startFrame.add(levelComboBox);

        String[] boardSize = {"3x3 Board", "4x4 Board", "5x5 Board"};
        x += 145;
        JComboBox boardComboBox = new JComboBox(boardSize);
        boardComboBox.setBounds(x, 100, 135, 50);
        startFrame.add(boardComboBox);
        startFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void startPlayLevelEasy() {
        //Object eMark = null; ****************************************************************************
        Game game = new GameGui(new HumanPlayer("X", eMark.X), new WhateverPlayer("O", eMark.O), new VoidRenderer());
        game.run();
        startFrame.setVisible(false);
    }

    public void startPlayLevelExpert() {
        Game game = new GameGui(new HumanPlayer("X", eMark.X), new CleverPlayer("O", eMark.O), new VoidRenderer());
        game.run();

        startFrame.setVisible(false);
    }

    public void startPlayAgainstMyself() {
        Game game = new GameGui(new HumanPlayer("X", eMark.X), new HumanPlayer("O", eMark.O), new VoidRenderer());
        game.run();

        startFrame.setVisible(false);
    }

    public static void main(String[] args) {
        ViewGame viewGame = new ViewGame();
    }
}

