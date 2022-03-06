package model.dynamicStructure;

import model.Piece;

import java.io.Serializable;

/**
 * @author Nerea Mañez
 */
public class Node implements Serializable {
    private Piece info;
    private Node next;

    /**
     * Create a new node
     *
     * @param piece
     */
    public Node(Piece piece) {
        info = piece;
        next = null;
    }

    /**
     * Return the next node
     *
     * @return a node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Set a new next node
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Return the piece if the node
     *
     * @return a piece
     */
    public Piece getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
