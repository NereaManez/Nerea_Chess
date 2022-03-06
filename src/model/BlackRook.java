package model;

public class BlackRook extends Rook {
    public BlackRook(Cell cell){
        super(cell,ChessType.BLACK_ROOK);
        place();
    }
}
