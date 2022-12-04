import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Dorin Tihon

/**
 *
 */
public class ttt implements ActionListener {
    char currentPlayer;
    JButton[][] buttons = new JButton[3][3];
    TttCell[][] cells = new TttCell[3][3];
    JFrame frame;
    JLabel textField;

    /**
     * @param e the event to be processed
     * @author Alacataba Inc.
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == buttons[i][j]) {
                    if (!cells[i][j].isOccupied()) {
                        if (currentPlayer == 'X') {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));

                            buttons[i][j].setForeground(new Color(175, 75, 75));
                            cells[i][j].setOccupied(true);
                            displayWinner();
                        } else {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));
                            buttons[i][j].setForeground(new Color(54, 87, 44));;
                            cells[i][j].setOccupied(true);
                            displayWinner();
                        }
                    } else {
                        JOptionPane winner = new JOptionPane();
                        JOptionPane.showMessageDialog(winner, "This cell is occupied", "Occupied", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

    }

    public void initialize() {
        currentPlayer = 'X';
        frame = new JFrame("Tic Tac si Toe");
        textField = new JLabel();
        JPanel buttonGrid = new JPanel();
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Current player: " + currentPlayer);
        textField.setForeground(new Color(24, 38, 89));
        textField.setFont(new Font("Arial", Font.BOLD, 50));
        frame.add(textField, BorderLayout.NORTH);


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

        // setting grid layout of 3 rows and 3 columns
        buttonGrid.setLayout(new GridLayout(3, 3));
        frame.add(buttonGrid);
        frame.setSize(550, 550);
        frame.setVisible(true);
    }

    public boolean rowWin() {
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

    public boolean columnWin() {
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

    public boolean diagonalWin() {
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;

        } else return buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("");
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner() {
        return rowWin() || columnWin() || diagonalWin();
    }

    public void resetGame() {
        frame.dispose();
        initialize();
    }

    public void displayWinner() {
        if (checkWinner()) {
            JOptionPane winner = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(winner, currentPlayer + " wins. Play again?", "Game over", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else System.exit(0);
        } else if (checkDraw()) {
            JOptionPane winner = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(winner, "Draw", "Game over", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
            } else System.exit(0);
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            textField.setText("Current player: " + currentPlayer);
        }
    }


    public static void main(String[] args) {
        ttt t = new ttt();
        t.initialize();

    }
}
