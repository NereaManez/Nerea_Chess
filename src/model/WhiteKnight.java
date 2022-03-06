package model;

public class WhiteKnight extends Knight {
    public WhiteKnight(Cell cell){
        super(cell,ChessType.WHITE_KNIGHT);
        place();
    }
}
