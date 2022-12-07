//Dorin Tihon

/**
 TttCell class to define the cell object that holds the player and if the cell is occupied
 */
public class TttCell {

    //Variables
    boolean occupied;
    char player;

    //Default constructor
    public TttCell() {
        occupied = false;
        player = ' ';
    }

    //Parameter constructor
    public TttCell(boolean occupied, char player) {
        this.occupied = occupied;
        this.player = player;
    }

    //isOccupied() method that returns the occupied variable
    public boolean isOccupied() {
        return occupied;
    }

    //setOccupied() method that sets the occupied variable to the given parameter
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    //getPlayer() method that returns the player variable
    public char getPlayer() {
        return player;
    }

    //setPlayer() method that sets the player variable to the given parameter
    public void setPlayer(char player) {
        this.player = player;
    }

    //toString method to print the TttCell object information
    @Override
    public String toString() {
        return "This cell is occupied:" + occupied +
                ", by player:" + player;
    }

    //equals method
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (this.getClass() == obj.getClass()) {
            TttCell other = (TttCell) obj;
            return occupied == other.isOccupied() &&
                    player == other.getPlayer();
        }
        return false;
    }
}
