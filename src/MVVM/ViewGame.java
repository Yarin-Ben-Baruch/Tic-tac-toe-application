package MVVM;

import Enums.eMark;
import Games.Game;
import Games.GameGui;
import Games.Tournament;
import Players.CleverPlayer;
import Players.HumanPlayer;
import Players.WhateverPlayer;
import Renderers.VoidRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ViewGame extends JFrame {

    private JFrame startFrame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JTextField titletextfield =new JTextField();
    private JButton[] optionsButtons = new JButton[4];
    private JPanel buttonPanel = new JPanel();
    private Tournament tournament;

    public ViewGame() {
        setStartFrame();
    }

    public void setStartFrame() {

        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setSize(500, 500);
        startFrame.setLayout(new BorderLayout());

        titlePanel.setBackground(Color.pink);

        titletextfield.setBackground(new Color(25,25,25));
        titletextfield.setForeground( Color.pink);
        titletextfield.setFont(new Font("Ink Free",Font.BOLD,70));
        titletextfield.setHorizontalAlignment(JLabel.CENTER);
        titletextfield.setText("Tic-Tac-Toe");
        titletextfield.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.setBackground(new Color(150,150,150));


        String[] titleForButtons = {"Easy", "Hard", "Against myself"};
        String playerType1 = "human", playerType2, selection, rendererType, numberOfRoundsStr = null;

        for(int i=0;i<3;i++) {
            optionsButtons[i] = new JButton(titleForButtons[i]);
            buttonPanel.add(optionsButtons[i]);
            optionsButtons[i].setFont(new Font("MV Boli",Font.BOLD,36));
            optionsButtons[i].setFocusable(false);

        }

        optionsButtons[0].addActionListener(e -> startPlayLevelEasy());
        optionsButtons[1].addActionListener(e -> startPlayLevelExpert());
        optionsButtons[2].addActionListener(e -> startPlayAgainstMyself());

        titlePanel.add(titletextfield);
        startFrame.add(titlePanel,BorderLayout.NORTH);
        startFrame.add(buttonPanel);
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
        Game game = new GameGui(new HumanPlayer("X", eMark.X), new WhateverPlayer("O", eMark.O), new VoidRenderer());
        game.run();

        startFrame.setVisible(false);
    }

    public static void main(String[] args) {
        ViewGame viewGame = new ViewGame();
    }


}
