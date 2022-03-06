package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public abstract class Knight extends Piece {

    /**
     * Create a knight
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public Knight(Cell cell, ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Calculates the cells to which the knight can be moved from the cell where it is located
     *
     * @return a list of coordinates of the cells where the piece can move to
     */
    public List<Coordinate> getNextMovements(){
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Coordinate position = cell.getCoordinate();
        Coordinate aux;
        //Up
        aux = position.up().upLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.up().upRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upLeft().left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upRight().right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        //Down
        aux = position.down().downLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.down().downRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downLeft().left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downRight().right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        return nextMovements;
    }

    /**
     * Check if the knight can move to the coordinate
     *
     * @param aux coordinate to move to
     * @return true if the piece can move to aux and false if not
     */
    public boolean canMoveTo(Coordinate aux){
        Board board = cell.getBoard();
        return board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor()!=getColor();
    }
}
