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
    private JLabel textfield;
    private JButton[] buttons;
    private ViewModel vm;
    private Player playerX;
    private Player playerO;
    private int turnToPlay;
    private Board myBoard;

    boolean player1_turn = true;
    public static int locationInBoard;



//    public static void main(String[] args) {
//        TTT.Renderers.TicTacToeApp t = new TTT.Renderers.TicTacToeApp();
//    }

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

    public void start() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground( Color.pink);
        textfield.setForeground(Color.white);
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        button_panel.setBackground(new Color(43, 206, 104));

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
    }

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
    }

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

    //עידכנתי במקום 3 ל board.size
    public int getLocationInBoard(int currentI) {
        return (currentI / Board.SIZE * 10 + 10) + (currentI % Board.SIZE + 1);
    }

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

    // עובר על הלוח וממקם את הסימנים
    public void showBoard(eMark[][] board) {
        int row;
        int col;

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {

            locationInBoard = getLocationInBoard(i);
            col = locationInBoard % 10 -1;
            row = locationInBoard / 10 -1;

            if (board[row][col] == eMark.X) {
                buttons[i].setForeground( Color.BLACK);
                buttons[i].setText("X");
                buttons[i].setEnabled(false);
            }
            else if (board[row][col] == eMark.O){
                buttons[i].setForeground(Color.pink);
                buttons[i].setText("O");
                buttons[i].setEnabled(false);
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

    // מציג למעלה את התור של מי שמשחק
    public void showTurn(String message) {

        textfield.setText(message);
    }

    public void showMessage(String message) {

        textfield.setText(message);

    }

    public boolean onlyHuman(Player currentPlayerX, Player secondPlayerO){
        int flag = 0;
        eMark sign = eMark.BLANK;

        if (!(currentPlayerX instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {

            }
            locationInBoard = currentPlayerX.getCoordinates(myBoard);
            flag = 1;
            sign = currentPlayerX.getDefaultSign();
        }
        if(!(secondPlayerO instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {

            }
            locationInBoard = secondPlayerO.getCoordinates(myBoard);
            System.out.println(locationInBoard);
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

