package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public abstract class Bishop extends Piece {

    /**
     * Create a bishop
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public Bishop(Cell cell, ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Calculates the cells to which the piece can be moved from the cell where it is located
     *
     * @param piece the piece to move
     * @return a list of coordinates of the cells where the piece can move to
     */
    public static List<Coordinate> getNextMovementsBishop(Piece piece) {
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = piece.cell.getBoard();
        Coordinate position = piece.cell.getCoordinate();
        Coordinate aux;

        //UpLeft
        boolean continueMove = true;
        aux = position;
        do {
            aux = aux.upLeft();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor())
                continueMove = false;
        } while (continueMove);

        //UpRight
        continueMove = true;
        aux = position;
        do {
            aux = aux.upRight();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor())
                continueMove = false;
        } while (continueMove);

        //DownLeft
        continueMove = true;
        aux = position;
        do {
            aux = aux.downLeft();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor())
                continueMove = false;
        } while (continueMove);

        //DownRight
        continueMove = true;
        aux = position;
        do {
            aux = aux.downRight();
            if (canMoveTo(aux,piece))
                nextMovements.add(aux);
            else
                continueMove = false;

            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor())
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
        return getNextMovementsBishop(this);
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
