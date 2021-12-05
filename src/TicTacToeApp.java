
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

public class TicTacToeApp extends JFrame implements ActionListener, iRenderer {

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
//        TicTacToeApp t = new TicTacToeApp();
//    }

    TicTacToeApp() {

    }

    TicTacToeApp(Player playerX, Player playerO, Board board) {
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
        button_panel.setBackground(new Color(150, 150, 150));

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

    // מציג למעלה את התור של מי שמשחק
    public void showTurn(String message) {

        textfield.setText(message);
    }

    public void showMessage(String message) {

        textfield.setText(message);

    }

    public boolean onlyHuman(Player currentPlayer, Player secondPlayer){
        int flag = 0;

        if (!(currentPlayer instanceof HumanPlayer)){

        }
        if(!(secondPlayer instanceof HumanPlayer)){

        }
    }

}


//    public void actionPerformed(ActionEvent e) {
//
//        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {
//
//            if (e.getSource() == buttons[i]) {
//
//                locationInBoard = getLocationInBoard(i);
//                System.out.println(locationInBoard);
//
//                if (player1_turn) {
//                    if (buttons[i].getText() == "") {
//                        buttons[i].setForeground( Color.BLACK);
//                        buttons[i].setText("X");
//                        player1_turn = false;
//                        textfield.setText("O turn");
//                    }
//                } else {
//                    if (buttons[i].getText() == "") {
//                        buttons[i].setForeground(Color.pink);
//                        buttons[i].setText("O");
//                        player1_turn = true;
//                        textfield.setText("X turn");
//                    }
//
//                }
//            }
//        }
//    }