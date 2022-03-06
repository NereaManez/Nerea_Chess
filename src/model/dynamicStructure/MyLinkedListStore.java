package model.dynamicStructure;

import model.ChessType;
import model.IDeletedPieceManager;
import model.Piece;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nerea Mañez
 */
public class MyLinkedListStore implements IDeletedPieceManager, Serializable {

    private List<Piece> pieces;

    /**
     * Create a new linked list store
     */
    public MyLinkedListStore() {
        this.pieces = new LinkedList<>();
    }

    /**
     * Count the number of pieces with this chess type
     *
     * @param chessType
     * @return the number of pieces with the chess type
     */
    @Override
    public int count(ChessType chessType) {
        int result = 0;

        for (Piece piece : pieces)
            if (piece.getChessType()==chessType)
                result++;

        return result;
    }

    /**
     * Add a new piece to the store
     *
     * @param piece
     */
    @Override
    public void add(Piece piece) {
        pieces.add(piece);
    }

    /**
     * Remove a piece from the store
     *
     * @param piece
     */
    @Override
    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Return the first piece in the store
     *
     * @return a piece
     */
    @Override
    public Piece getFirst() {
        return pieces.get(0);
    }
}
