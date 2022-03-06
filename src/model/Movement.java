package model;

import java.io.Serializable;

public class Movement implements Serializable {
    private Coordinate c1, c2;
    private Piece piece;

    public Movement (Coordinate c1, Coordinate c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Movement (Coordinate c1, Coordinate c2, Piece piece) {
        this.c1 = c1;
        this.c2 = c2;
        this.piece = piece;
    }

    public Coordinate getC1() {
        return c1;
    }
    public Coordinate getC2() {
        return c2;
    }
    public Piece getPiece() {
        return piece;
    }
}
