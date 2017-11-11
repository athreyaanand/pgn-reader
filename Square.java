public class Square {
    private char[] validRanks = {'1', '2', '3', '4', '5', '6', '7', '8'};
    private char[] validFiles = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private char file, rank;

    public Square(char file, char rank) {
        this.file = file;
        this.rank = rank;
        if (!contains(validFiles, file) || !contains(validRanks, rank)) {
            throw new InvalidSquareException("" + file + rank);
        }
    }

    public Square(String name) {
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
        if (!contains(validFiles, file) || !contains(validRanks, rank)
            || name.length() != 2) {
            throw new InvalidSquareException(name);
        }
    }

    public boolean contains(char[] main, char key) {
        for (int i = 0; i < main.length; i++) {
            if (main[i] == key) {
                return true;
            }
        }
        return false;
    }

    public char getFile() {
        return this.file;
    }

    public char getRank() {
        return this.rank;
    }

    public String toString() {
        return Character.toString(file) + Character.toString(rank);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square compare = (Square) other;
        if (this.file == compare.getFile() && this.rank == compare.getRank()) {
            return true;
        }
        return false;
    }

    /**
     * Returns the hash code value for this square.
     *
     * @return hash code value (int) for this square
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
