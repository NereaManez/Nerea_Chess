package model;

public class BlackBishop extends Bishop {
    public BlackBishop(Cell cell){
        super(cell,ChessType.BLACK_BISHOP);
        place();
    }
}
