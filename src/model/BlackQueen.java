package model;

public class BlackQueen extends Queen{
    public BlackQueen(Cell cell){
        super(cell,ChessType.BLACK_QUEEN);
        place();
    }
}
