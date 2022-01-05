package Renderers;

import Boards.Board;
import Enums.eMark;
import MVVM.ViewModel;
import Players.HumanPlayer;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRenderer extends JFrame implements ActionListener, iRenderer {

    private JFrame frame;
    private JPanel title_panel;
    private JPanel button_panel;
    private JPanel statisticsPanel;
    private JLabel textfield;
    private JButton[] buttons;
    private ViewModel vm;
    private Player playerX;
    private Player playerO;
    private int turnToPlay;
    private Board myBoard;

    boolean player1_turn = true;
    public static int locationInBoard;


    GuiRenderer() {

    }

    public GuiRenderer(Player playerX, Player playerO, Board board) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.myBoard = board;
        turnToPlay = 0;
    }

    public void init() {
        frame = new JFrame();
        title_panel = new JPanel();
        button_panel = new JPanel();
        textfield = new JLabel();
        buttons = new JButton[Board.SIZE * Board.SIZE];
    }

    /**
     * 'start' method is method that set all SWING buttons/textfields and more..
     */
    public void start() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 700);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(0xB0B0B0));
        textfield.setForeground(new Color(0xF1C8C8));
        textfield.setFont(new Font("Microsoft Himalaya", Font.BOLD, 85));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(Board.SIZE, Board.SIZE, 20 , 20));
        button_panel.setBackground(new Color(0, 0, 0));



        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Microsoft Himalaya", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        statisticsPanel = new JPanel();
        statisticsPanel.setPreferredSize(new Dimension(100, 50));
        statisticsPanel.add(new JLabel("Statistics:"));

        title_panel.add(textfield);
        frame.add(statisticsPanel, BorderLayout.SOUTH);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
    }

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
    }

    /**
     * 'actionPerformed' is method that set action to the buttons.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {

            if (e.getSource() == buttons[i]) {
                locationInBoard = getLocationInBoard(i);

                eMark sign;
                if(turnToPlay % 2 == 0) {
                    sign = playerX.getDefaultSign();
                }
                else {
                    sign = playerO.getDefaultSign();
                }

                int col = locationInBoard % 10;
                int row = locationInBoard / 10;
                vm.getCoordinatesFromGui(row, col, sign);
                turnToPlay++;

                if(!onlyHuman(playerX, playerO)){
                    turnToPlay++;
                }
            }
        }
    }

    /**
     * 'getLocationInBoard' is method that give the current location in the board.
     *
     * @return
     */
    public int getLocationInBoard(int currentI) {
        return (currentI / Board.SIZE * 10 + 10) + (currentI % Board.SIZE + 1);
    }

    /**
     * 'renderBoard' is method that rendered the board.
     * @param board
     */
    @Override
    public void renderBoard(Board board) {

        int row, col;
        row = locationInBoard / 10;
        col = locationInBoard % 10;
        if (player1_turn) {

            board.putMark(eMark.X,row,col);
        }else {
            board.putMark(eMark.O,row,col);
        }
    }


    /**
     * 'showBoard' is method that goes across the board and places the marks.
     * * @param board -> board is argument of Board class.
     */
    public void showBoard(eMark[][] board) {
        int row;
        int col;

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {

            locationInBoard = getLocationInBoard(i);
            col = locationInBoard % 10 -1;
            row = locationInBoard / 10 -1;

            if (board[row][col] == eMark.X) {
                buttons[i].setForeground( Color.BLACK);
                //buttons[i].setText("X");
                buttons[i].setIcon(new ImageIcon("src/images/xIcon.png"));
                buttons[i].setEnabled(false);
            }
            else if (board[row][col] == eMark.O){
                buttons[i].setForeground(Color.pink);
                buttons[i].setIcon(new ImageIcon("src/images/oIcon.png"));
                //buttons[i].setText("O");
                buttons[i].setEnabled(true);
                }
            }

        }

    public void disableAll(eMark mark) {
        JOptionPane.showMessageDialog(frame,
                "The winner is "+ mark,
                "WINNER",
                JOptionPane.CLOSED_OPTION);
        System.exit(0);
    }

    /**
     * 'showTurn is method that shows up the turn of the player.
     * @param message -> message to text field.
     */
    public void showTurn(String message) {
        textfield.setText(message);
    }

    public void showMessage(String message) {
        textfield.setText(message);
    }

    /**
     * 'onlyHuman' is method returned if there are human player or computer player.
     * @param currentPlayerX -> Player 1
     * @param secondPlayerO -> Player 2
     * @return
     */
    public boolean onlyHuman(Player currentPlayerX, Player secondPlayerO){

        int flag = 0;
        eMark sign = eMark.BLANK;

        if (!(currentPlayerX instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {}

            locationInBoard = currentPlayerX.getCoordinates(myBoard);
            flag = 1;
            sign = currentPlayerX.getDefaultSign();
        }
        if(!(secondPlayerO instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {}

            locationInBoard = secondPlayerO.getCoordinates(myBoard);
            flag = 1;
            sign = secondPlayerO.getDefaultSign();
        }
        if(flag == 1) {
            int col = locationInBoard % 10;
            int row = locationInBoard / 10;
            vm.getCoordinatesFromGui(row, col, sign);
            return false;
        }

        return true;
    }

}

