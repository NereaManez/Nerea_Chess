package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public class Queen extends Piece {

    /**
     * Create a queen
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public Queen(Cell cell,ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Calculates the cells to which the queen can be moved from the cell where it is located
     *
     * @return a list of coordinates of the cells where the piece can move to
     */
    public List<Coordinate> getNextMovements() {
        List<Coordinate> nextMovements = new ArrayList<>();
        nextMovements.addAll(Bishop.getNextMovementsBishop(this));
        nextMovements.addAll(Rook.getNextMovements(this));
        return nextMovements;
    }
}
