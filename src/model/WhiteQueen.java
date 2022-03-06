package model;

public class WhiteQueen extends Queen{
    public WhiteQueen(Cell cell){
        super(cell,ChessType.WHITE_QUEEN);
        place();
    }
}
