/**
 * Represents a Square on the board
 *
 * @author aanand76
 * @version 1.0
 */
public class Square {
    private char rank;
    private char file;
    private String name;

    /**
     * an arg constructor for Square
     * @param  file represents file on board
     * @param  rank represents rank on board
     */
    public Square(char file, char rank) {
        this("" + file + rank);
    }

    /**
     * an arg constructor for Square
     * @param  name contains file and rank
     */
    public Square(String name) {
        this.name = name;
        if (name != null && name.length() == 2) {
            file = name.charAt(0);
            rank = name.charAt(1);
            if (file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8') {
                this.name = name;

            } else {
                throw new InvalidSquareException(name);
            }
        } else {
            throw new InvalidSquareException(name);
        }
    }

    /**
     * gets the file of square
     * @return a char
     */
    public char getFile() {
        return file;
    }

    /**
     * gets the rank of square
     * @return a char
     */
    public char getRank() {
        return rank;
    }

    /**
     * gets the name of square
     * @return a string
     */
    public String toString() {
        return name;
    }

    /**
     * an equals method to compare squares
     * @param  o that compares object to this
     * @return a boolean representing equality
     */
    public boolean equals(Object o) {
        if (o instanceof Square) {
            Square that = (Square) o;
            return that.rank == rank && that.file == file;
        } else {
            return false;
        }
    }
}
