import java.util.*;

/**
 * Represents a convenient collection class for Squares that
 * implements Set.
 *
 * @author aanand76
 * @version 1.0
 */
public class SquareSet implements Set<Square> {
    private Square[] squares;
    private String masterString = "";
    private int arrayIndex = 0;
    private char[] validRanks = {'1', '2', '3', '4', '5', '6', '7', '8'};
    private char[] validFiles = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    /**
     * Noarg constructor for SquareSet.
     */
    public SquareSet() {
        squares = new Square[100];
    }

    /**
     * Collection constructor for SquareSet.
     *
     * @param collection is added as defaukt values of set
     */
    public SquareSet(Collection<Square> collection) {
        squares = new Square[100];
        for (int i = 0; i < collection.size(); i++) {
            this.add((Square) collection.toArray()[i]);
        }
    }

    /**
     * Adds the specified element to this set if it is not already
     * present.
     *
     * @param  E e
     * @return true if added successfully
     */
    @Override
    public boolean add(Square e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Square newSquare = (Square) e;
        if (!checkValidity(newSquare)) {
            throw new InvalidSquareException(newSquare.toString());
        }
        if (!(masterString.contains(newSquare.toString()))) {
            if (squares.length >= arrayIndex) {
                squares = increaseArray(squares);
            }
            squares[arrayIndex] = newSquare;
            arrayIndex++;
            masterString += newSquare.toString();
            return true;
        }
        return false;
    }

    /**
     * Adds all of the elements in the specified collection to
     * this set if they're not already present
     *
     * @param  E e
     * @return true if all values successfully added
     */
    @Override
    public boolean addAll(Collection<? extends Square> c) {
        cleanArray();
        int added = 0;
        for (int i = 0; i < c.size(); i++) {
            if (!masterString.contains(c.toArray()[i].toString())
                && c != null) {
                added++;
                this.add((Square) c.toArray()[i]);
            }
        }
        if (added == 0) {
            return false;
        }
        return true;
    }

    /**
     * Removes all of the elements from this set.
     */
    @Override
    public void clear() {
        squares = new Square[squares.length];
        arrayIndex = 0;
    }

    /**
     * Removes a certain element within squareset.
     *
     * @param  Object o that must be removed
     * @return  true if successfully removed
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Square)) {
            return false;
        }
        cleanArray();
        Square newSquare = (Square) o;
        if (masterString.contains(newSquare.toString())) {
            masterString.replace(newSquare.toString(), "");
            for (int i = 0; i < squares.length; i++) {
                if (squares[i].toString().equals(newSquare.toString())) {
                    squares[i] = null;
                }
            }
            cleanArray();
            return true;
        }
        return false;
    }

    /**
     * Removes all elements within c.
     *
     * @param  Collection<?> c that contains elemts to remove from current
     * @return  true if successfully removed
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new java.lang.UnsupportedOperationException();
    }

    /**
     * retains all elements within c
     * @param  Collection<?> c that contains elemts to retain from current
     * @return  true if successfully retains
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new java.lang.UnsupportedOperationException();
    }

    /**
     * Returns true if this set contains the specified element.
     * @param  Object o             [description]
     * @return  true if the SquareSet contains the object
     */
    @Override
    public boolean contains(Object o) {
        if (null == o) {
            return false;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square newSquare = (Square) o;
        return masterString.contains(newSquare.toString());
    }

    /**
     * Returns true if this set contains all of the elements of the
     * specified collection.
     *
     * @param  Collection<?> c
     * @return true if this set contains all of the elements of the
     *         specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < c.size(); i++) {
            if (!masterString.contains(c.toArray()[i].toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares the specified object with this set for equality.
     *
     * @param  an Object o in order to compare to this set
     * @return true if the object is equivalent to this set
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Collection)) {
            return false;
        }
        Collection compare = (Collection) other;
        if (this.size() != compare.size()) {
            return false;
        }
        return containsAll(compare);
    }

    /**
     * Returns the hash code value for this set.
     *
     * @return hash code value for this set
     */
    @Override
    public int hashCode() {
        cleanArray();
        int hashcode = 0;
        for (int i = 0; i < squares.length; i++) {
            hashcode += squares[i].hashCode();
        }
        return hashcode;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<Square> iterator() {
        cleanArray();
        return new SquareIterator(squares);
    }

    /**
     * Returns the number of elements in this set (its cardinality).
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        cleanArray();
        return squares.length;
    }

    /**
     * Returns an array containing all of the elements in this set.
     *
     * @return an array containing all of the elements in the set
     */
    @Override
    public Object[] toArray() {
        cleanArray();
        return squares;
    }

    /**
     * Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param  a T[] a in order to distinguish the type of the returned array
     * @return an array of type T containing all elements in the set
     */
    @Override
    public <T> T[] toArray(T[] a) {
        cleanArray();
        if (a == null) {
            throw new NullPointerException();
        }
        return (T[]) squares;
    }

    /**
     * Cleans array and removes any empty elements of array
     */
    private void cleanArray() {
        ArrayList<Square> tempList = new ArrayList();
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null) {
                tempList.add(squares[i]);
            }
        }
        squares = new Square[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            squares[i] = tempList.get(i);
        }
        arrayIndex = tempList.size();
    }

    /**
     * Checks if the passed square is a valid square or not.
     *
     * @param  a square needing to be checked for validity
     * @return returns true if the square is valid
     */
    private boolean checkValidity(Square square) {
        if (square == null) {
            throw new NullPointerException();
        }
        char file = square.toString().charAt(0);
        char rank = square.toString().charAt(1);
        if (!containsValue(validFiles, file) || !containsValue(validRanks, rank)
            || square.toString().length() != 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a value is in an array.
     *
     * @param  main is the array in which we search
     * @param  key is the value we check for inside main
     * @return true if the key is contained within main
     */
    public boolean containsValue(char[] main, char key) {
        for (int i = 0; i < main.length; i++) {
            if (main[i] == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a value is in an array.
     *
     * @param  squareArr is the array in which we search
     * @return true if the key is contained within main
     */
    public Square[] increaseArray(Square[] squareArr) {
        Square[] tempSquares = new Square[squareArr.length + 10];
        for (int i = 0; i < squareArr.length; i++) {
            tempSquares[i] = squareArr[i];
        }
        return tempSquares;
    }

    /**
     *An iterator class used by SquareSet
     */
    private class SquareIterator implements Iterator<Square> {
        private Square[] squares;
        private int cursor, end;

        /**
         * A constructor for SquareIterator.
         * @param  Square[] squares taken in to determine cursor and end
         */
        public SquareIterator(Square[] squares) {
            this.squares = squares;
            this.cursor = 0;
            if (squares.length != 0) {
                this.end = squares.length - 1;
            }
        }

        /**
         * Checks if theres a value after the current cursor
         * @return true if a value stil exists after the cursor
         */
        public boolean hasNext() {
            return this.cursor < end;
        }

        /**
         * Returns the next square if it exists else an exception
         * @return a Square or an exception depending on the following value
         */
        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return squares[cursor++];
        }
    }
}
