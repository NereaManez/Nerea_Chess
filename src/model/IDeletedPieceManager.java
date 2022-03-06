package model;

public interface IDeletedPieceManager {
    int count(ChessType chessType);
    void add(Piece piece);
    void remove(Piece piece);
    Piece getFirst();
}
