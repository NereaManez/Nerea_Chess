package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nerea Mañez
 */
public class Coordinate implements Serializable {
    private char letter;
    private int number;

    /**
     * Create a neew coordinate
     *
     * @param letter
     * @param number
     */
    public Coordinate(char letter, int number) {
        this.letter = String.valueOf(letter).toUpperCase().charAt(0);
        this.number = number;
    }

    /**
     * Return the coordinate located at the right
     *
     * @return a coordinate
     */
    public Coordinate right() {
        return new Coordinate((char) (letter + 1), number);
    }

    /**
     * Return the coordinate located at the left
     *
     * @return a coordinate
     */
    public Coordinate left() {
        return new Coordinate((char) (letter - 1), number);
    }

    /**
     * Return the coordinate located down
     *
     * @return a coordinate
     */
    public Coordinate down() {
        return new Coordinate(letter, number + 1);
    }

    /**
     * Return the coordinate located up
     *
     * @return a coordinate
     */
    public Coordinate up() {
        return new Coordinate(letter, number - 1);
    }

    /**
     * Return the coordinate located at the diagonal up-left
     *
     * @return a coordinate
     */
    public Coordinate upLeft() {
        return up().left();
    }

    /**
     * Return the coordinate located at the diagonal up-right
     *
     * @return a coordinate
     */
    public Coordinate upRight() {
        return up().right();
    }

    /**
     * Return the coordinate located at the diagonal down-left
     *
     * @return a coordinate
     */
    public Coordinate downLeft() {
        return down().left();
    }

    /**
     * Return the coordinate located at the diagonal down-right
     *
     * @return a coordinate
     */
    public Coordinate downRight() {
        return down().right();
    }

    /**
     * Return the letter of the coordinate
     *
     * @return a char
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Return the number of the coordinate
     *
     * @return a integer
     */
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            return ((Coordinate) o).letter == letter && ((Coordinate) o).number==number;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return number+letter;
    }

    public int compareTo (Coordinate c) {
        if (c.getLetter()!=this.letter) return c.getLetter()-this.letter;
        return (c.getNumber()!=this.number)?c.getNumber()-this.number:0;
    }

    @Override
    public String toString() {
        return "(" + letter + "," + number + ")";
    }
}
