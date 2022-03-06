package model;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Piece{
    public WhitePawn(Cell cell){
        super(cell,ChessType.WHITE_PAWN);
        place();
    }

    public List<Coordinate> getNextMovements() {
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Coordinate position = cell.getCoordinate();
        Coordinate aux = position;

        aux = aux.down();
        if (board.containsCellAt(aux) && !board.containsPieceAt(aux)) {
            nextMovements.add(aux);
            if (position.getNumber()==2 && !board.containsPieceAt(aux.down())) {
                nextMovements.add(aux.down());
            }
        }

        aux = position.downLeft();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor()!=getColor())
            nextMovements.add(aux);

        aux = position.downRight();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor()!=getColor())
            nextMovements.add(aux);

        return nextMovements;
    }

    public boolean canMoveTo(Coordinate aux){
        Board board = cell.getBoard();
        return board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor()!=getColor();
    }
}
