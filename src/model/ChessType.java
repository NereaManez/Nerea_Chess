package model;

import model.PieceColor;

/**
 * @author Nerea Mañez
 */
public enum ChessType {
    WHITE_KING("♚", PieceColor.WHITE),
    WHITE_QUEEN("♛", PieceColor.WHITE),
    WHITE_ROOK("♜", PieceColor.WHITE),
    WHITE_BISHOP("♝", PieceColor.WHITE),
    WHITE_KNIGHT("♞", PieceColor.WHITE),
    WHITE_PAWN("♟", PieceColor.WHITE),
    BLACK_KING("♚", PieceColor.BLACK),
    BLACK_QUEEN("♛", PieceColor.BLACK),
    BLACK_ROOK("♜", PieceColor.BLACK),
    BLACK_BISHOP("♝", PieceColor.BLACK),
    BLACK_KNIGHT("♞", PieceColor.BLACK),
    BLACK_PAWN("♟", PieceColor.BLACK);

    private final String shape;
    private final PieceColor color;

    /**
     * Create a new chess type
     *
     * @param shape
     * @param color
     */
    ChessType(String shape, PieceColor color) {
        this.shape = shape;
        this.color = color;
    }

    /**
     * Returns the shape of the shape type
     *
     * @return a string, the shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * Returns the color of the shape type
     *
     * @return a piece color
     */
    public PieceColor getColor() {
        return color;
    }
}
