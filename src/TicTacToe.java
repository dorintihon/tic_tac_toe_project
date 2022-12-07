import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Dorin Tihon

/**
 The class TicTacToe which creates a GUI interface that implements ActionListener.
 It has all the methods that are necessary for making the game working.
 It holds the current player, and it creates the JFrame where the 2D array of buttons is
 implemented.
 It has the main method where class is initialized
 */
public class TicTacToe implements ActionListener {

    //Variables
    char currentPlayer;
    JButton[][] buttons = new JButton[3][3];
    TttCell[][] cells = new TttCell[3][3];
    JFrame frame;
    JLabel textField;


    //actionPerformed() method that performs the action when the
    //selected button is pressed.
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                //if method to get the button on which the mouse clicks on
                if (e.getSource() == buttons[i][j]) {

                    //if method to check if the selected cell is occupied
                    if (!cells[i][j].isOccupied()) {

                        //if method to perform action if the current player is X
                        if (currentPlayer == 'X') {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));
                            buttons[i][j].setForeground(new Color(175, 75, 75));
                            cells[i][j].setOccupied(true);
                            displayWinner();
                        }
                        //else method to perform action if the current player is O
                        else {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));
                            buttons[i][j].setForeground(new Color(54, 87, 44));
                            cells[i][j].setOccupied(true);
                            displayWinner();
                        }
                    }
                    //else method that displays a window if the selected cell is occupied
                    else {
                        JOptionPane winner = new JOptionPane();
                        JOptionPane.showMessageDialog(winner, "This cell is occupied", "Occupied", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    //startGame() method that creates the JFrame and formats that frame
    //in the proper way.
    public void startGame() {
        currentPlayer = 'X';
        frame = new JFrame("Tic Tac si Toe");
        frame.setSize(550, 550);

        //JLabel object that shows at the top of the frame the current player
        textField = new JLabel();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Current player: " + currentPlayer);
        textField.setForeground(new Color(24, 38, 89));
        textField.setFont(new Font("Arial", Font.BOLD, 50));
        frame.add(textField, BorderLayout.NORTH);

        //JPanel object that define the buttons
        JPanel buttonGrid = new JPanel();

        //Setting grid layout of 3 rows and 3 columns
        buttonGrid.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                cells[i][j] = new TttCell();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 180));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                buttonGrid.add(buttons[i][j]);
            }
        }
        frame.add(buttonGrid);
        frame.setVisible(true);
    }


    //checkRow() method that checks if there are 3 X's or 3 O's in a row
    public boolean checkRow() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
            i++;
        }
        return false;
    }

    //checkColumn() method that checks if there are 3 X's or 3 O's in a column
    public boolean checkColumn() {
        int i = 0;
        int x = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i][x].getText().equals(buttons[i + 1][x].getText()) &&
                    buttons[i][x].getText().equals(buttons[i + 2][x].getText()) &&
                    !buttons[i][x].getText().equals("")) {
                return true;
            }
            x++;
        }
        return false;
    }

    //checkDiagonal() method that checks if there are 3 X's or 3 O's in a diagonal
    public boolean checkDiagonal() {
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;

        } else return buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("");
    }

    //checkWinner() method that check if there is a winning combination
    public boolean checkWinner() {
        return checkRow() || checkColumn() || checkDiagonal();
    }

    //checkDraw() method that check if there are no empty cells
    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!cells[i][j].isOccupied()) {
                    return false;
                }
            }
        }
        return true;

    }


    //displayWinner() method that create a window with the winner and option to play again
    public void displayWinner() {
        if (checkWinner()) {
            JOptionPane winner = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(winner, currentPlayer + " wins. Play again?", "Game over", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else System.exit(0);
        } else if (checkDraw()) {
            JOptionPane winner = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(winner, "Draw. Play again?", "Game over", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else System.exit(0);
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            textField.setText("Current player: " + currentPlayer);
        }
    }

    //resetGame() method that resets the game
    public void resetGame() {
        frame.dispose();
        startGame();
    }

    //main method
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.startGame();
    }
}
