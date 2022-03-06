package model;

public class WhiteRook extends Rook {
    public WhiteRook(Cell cell){
        super(cell,ChessType.WHITE_ROOK);
        place();
    }
}
