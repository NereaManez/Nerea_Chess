package model;

import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Piece{
    public BlackPawn(Cell cell){
        super(cell,ChessType.BLACK_PAWN);
        place();
    }

    public List<Coordinate> getNextMovements() {
        List<Coordinate> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Coordinate position = cell.getCoordinate();
        Coordinate aux = position;

        aux = aux.up();
        if (board.containsCellAt(aux) && !board.containsPieceAt(aux)) {
            nextMovements.add(aux);
            if (position.getNumber()==7 && !board.containsPieceAt(aux.up())) {
                nextMovements.add(aux.up());
            }
        }

        aux = position.upLeft();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor()!=getColor())
            nextMovements.add(aux);

        aux = position.upRight();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                board.getCellAt(aux).getPiece().getColor()!=getColor())
            nextMovements.add(aux);

        return nextMovements;
    }
}
