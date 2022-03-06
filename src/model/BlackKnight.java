package model;

public class BlackKnight extends Knight {
    public BlackKnight(Cell cell){
        super(cell,ChessType.BLACK_KNIGHT);
        place();
    }
}
