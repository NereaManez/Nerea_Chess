package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public abstract class King extends Piece{

    /**
     * Create a king
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public King(Cell cell, ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Calculates the cells to which the king can be moved from the cell where it is located
     *
     * @return a list of coordinates of the cells where the piece can move to
     */
    public List<Coordinate> getNextMovements(){
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Coordinate position = cell.getCoordinate();
        Coordinate aux;
        //Up
        aux = position.up();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //UpRight
        aux = position.upRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //UpLeft
        aux = position.upLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //Right
        aux = position.right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //Left
        aux = position.left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //Down
        aux = position.down();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //DownLeft
        aux = position.downLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        //DownRight
        aux = position.downRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }

        return nextMovements;
    }

    /**
     * Check if the piece can move to the coordinate
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
