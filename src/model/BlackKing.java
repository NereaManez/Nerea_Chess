package model;

public class BlackKing extends King{
    public BlackKing(Cell cell){
        super(cell,ChessType.BLACK_KING);
        place();
    }
}
