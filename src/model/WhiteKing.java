package model;

public class WhiteKing extends King{
    public WhiteKing(Cell cell){
        super(cell,ChessType.WHITE_KING);
        place();
    }
}
