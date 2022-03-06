package model.dynamicStructure;

import model.ChessType;
import model.IDeletedPieceManager;
import model.Piece;

import java.io.Serializable;

/**
 * @author Nerea Mañez
 */
public class MyList implements IDeletedPieceManager, Serializable {
    private Node head;
    private int size;

    /**
     * Add a new piece to the list
     *
     * @param piece
     */
    @Override
    public void add(Piece piece) {
        Node aux = new Node(piece);

        if (head != null)
            aux.setNext(head);

        head = aux;
        size++;
    }

    /**
     * Return the first piece in the list
     *
     * @return a piece
     */
    @Override
    public Piece getFirst() {
        if (head == null)
            return null;
        else {
            Piece p = head.getInfo();
            head = head.getNext();

            return p;
        }
    }

    /**
     * Remove a piece from the list
     *
     * @param piece
     */
    @Override
    public void remove(Piece piece) {
        if (head == null)
            return;

        if (head.getInfo().equals(piece)) {
            head = head.getNext();
            size--;
        } else {
            Node aux2 = head, aux1 = head.getNext();

            while (aux1 != null && aux1.getInfo() != piece) {
                aux2 = aux1;
                aux1 = aux1.getNext();
            }
        }
    }

    /**
     * Count the number of pieces with this chess type
     *
     * @param chessType
     * @return the number of pieces with the chess type
     */
    @Override
    public int count(ChessType chessType) {
        int count = 0;

        Node aux = head;

        while (aux != null) {
            if (aux.getInfo().getChessType().equals(chessType))
                count++;

            aux = aux.getNext();
        }

        return count;
    }
}
