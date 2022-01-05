package MVVM.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FirstScreenGame {


    private JFrame startFrame = new JFrame("Tic Tac Toe By YDM");
    private JButton startButton = new JButton("Start New Game");

    public FirstScreenGame() {
        setTheFirstScreen();
    }

    public void setTheFirstScreen() {
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(650, 620);
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/images/tictactoeicon.png");
        startFrame.setIconImage(icon);

        try {
            startFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/FirstScreenImage.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton.setBounds(245, 520, 150, 40);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0xFFFFFF));
        startFrame.add(startButton);

        startButton.addActionListener(e -> startNewGame());

        startFrame.setVisible(true);
    }

    public static void main(String[] args) {
        FirstScreenGame firstScreenGame = new FirstScreenGame();
    }

    public void startNewGame()
    {
        startFrame.dispose();
        OptionScreenGameView viewGame = new OptionScreenGameView();
    }
}
