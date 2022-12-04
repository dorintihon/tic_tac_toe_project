public class TttCell {
    boolean occupied;
    char player;

    public TttCell() {
        occupied = false;
        player = ' ';
    }

    public TttCell(boolean occupied, char player) {
        this.occupied = occupied;
        this.player = player;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "This cell is occupied:" + occupied +
                ", by player:" + player;
    }

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
