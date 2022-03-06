package model;

public class WhiteBishop extends Bishop {
    public WhiteBishop(Cell cell){
        super(cell,ChessType.WHITE_BISHOP);
        place();
    }
}
