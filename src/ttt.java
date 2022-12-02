import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ttt implements ActionListener{
    TttCell cell = new TttCell();
    char currentPlayer = 'x';
    JButton[][] buttons = new JButton[3][3];
    TttCell[][] cells = new TttCell[3][3];
    ttt(){
    }



    public void actionPerformed(ActionEvent e) {
        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(e.getSource() == buttons[i][j]){
                    if (!cells[i][j].isOccupied()) {
                        if (currentPlayer == 'x') {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));
                            currentPlayer = 'o';
                            buttons[i][j].setBackground(Color.RED);
                            cells[i][j].setOccupied(true);
                        } else {
                            cells[i][j].setPlayer(currentPlayer);
                            buttons[i][j].setText(String.valueOf(cells[i][j].getPlayer()));
                            currentPlayer = 'x';
                            buttons[i][j].setBackground(Color.BLUE);
                            cells[i][j].setOccupied(true);

                        }
                    }
                    else {
                        JOptionPane winner = new JOptionPane();
                        JOptionPane.showMessageDialog(winner, "This cell is occupied", "Occupied", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    public void initialize(){
        JFrame frame = new JFrame();

        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {
                buttons[i][j] = new JButton();
                cells[i][j] = new TttCell();

                buttons[i][j].setBackground(Color.WHITE);

                buttons[i][j].addActionListener(this);
                frame.add(buttons[i][j]);
            }
        }

        // setting grid layout of 3 rows and 3 columns
        frame.setLayout(new GridLayout(3,3));
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    public boolean rowWin() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    buttons[i][0].getText().charAt(0) != '-') {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean columnWin(){
        int i = 0;
        int x = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i][x].getText().equals(buttons[i+1][x].getText()) &&
                    buttons[i][x].getText().equals(buttons[i+2][x].getText()) &&
                    buttons[i][x].getText().charAt(0) != '-') {
                return true;
            }
            x++;
        }
        return false;
    }
    public boolean diagonalWin(){
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                buttons[0][0].getText().charAt(0) != '-') {
            return true;
        }
        else if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                buttons[0][2].getText().charAt(0) != '-') {
            return true;
        }
        else return false;
    }

    public boolean checkWinner(){
        if (rowWin() || columnWin() || diagonalWin()){
            return true;
        }
        else return false;
    }

    public void resetGame(){
        currentPlayer = 'x';
        for (int i=0; i < buttons.length; i++) {
            for (int j=0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setText(" ");
            }
        }
    }

    public void displayWinner(){
        if (checkWinner()){
            JOptionPane winner = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(winner, currentPlayer + " wins. Play again?","Game over", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                resetGame();
            }else System.exit(0);
        }
    }


    public static void main(String[] args) {
        ttt t = new ttt();
        t.initialize();

    }
}
