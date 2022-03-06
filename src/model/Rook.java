package model;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public abstract class Rook extends Piece {

    /**
     * Create a bishop
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public Rook(Cell cell, ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Calculates the cells to which the rook can be moved from the cell where it is located
     *
     * @param piece the piece to move
     * @return a list of coordinates of the cells where the piece can move to
     */
    public static List<Coordinate> getNextMovements(Piece piece){
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = piece.cell.getBoard();
        Coordinate position = piece.cell.getCoordinate();
        Coordinate aux;

        //Up
        boolean continueMove = true;
        aux = position;
        do {
            aux = aux.up();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (havePiece(aux,piece))
                continueMove = false;
        } while (continueMove);

        //Down
        continueMove = true;
        aux = position;
        do {
            aux = aux.down();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (havePiece(aux,piece))
                continueMove = false;
        } while (continueMove);

        //Left
        continueMove = true;
        aux = position;
        do {
            aux = aux.left();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (havePiece(aux,piece))
                continueMove = false;
        } while (continueMove);

        //Right
        continueMove = true;
        aux = position;
        do {
            aux = aux.right();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (havePiece(aux,piece))
                continueMove = false;
        } while (continueMove);

        return nextMovements;
    }

    /**
     * Calculates the cells to which these piece can be moved from the cell where it is located
     *
     * @return a list of coordinates of the cells where the piece can move to
     */
    public List<Coordinate> getNextMovements() {
        return getNextMovements(this);
    }

    /**
     * Check if a coordinate contains a piece
     *
     * @param aux the coordinate to check
     * @param piece compare the piece color with the color of the coordinate's piece
     * @return true if the coordinate have a piece and if the colors are not equal
     */
    public static boolean havePiece(Coordinate aux, Piece piece) {
        Board board = piece.cell.getBoard();
        return board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor()!=piece.getColor();
    }

    /**
     * Check if the piece can move to the coordinate
     *
     * @param aux coordinate to move to
     * @param piece the piece that we want to move
     * @return true if the piece can move to aux and false if not
     */
    public static boolean canMoveTo(Coordinate aux, Piece piece){
        Board board = piece.cell.getBoard();
        return board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor()!=piece.getColor();
    }
}
