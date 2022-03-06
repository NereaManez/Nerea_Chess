package model;


import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;
import java.util.List;
import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author Nerea Mañez
 */
public abstract class Piece implements Serializable {
    private ChessType chessType;
    protected Cell cell;

    /**
     * Create a piece
     *
     * @param cell the cell where it is initially located
     * @param chessType
     */
    public Piece(Cell cell, ChessType chessType) {
        this.chessType = chessType;
        this.cell = cell;
    }

    /**
     * Set a piece in a cell
     */
    public void place() {
        cell.setPiece(this);
    }

    /**
     * Calculates the cells to which a piece can be moved from the cell where it is located
     *
     * @return a list of coordinates of the cells where the piece can move to
     */
    public abstract List<Coordinate> getNextMovements();

    /**
     * Move a piece from the coordinate where is located to an other coordinate
     *
     * @param c final the coordinate
     * @return true if the piece can be moved to the coordinate and false if not
     */
    public boolean moveTo(Coordinate c) {

        if (cell==null || !cell.getBoard().containsCellAt(c))
            return false;

        if (getNextMovements().contains(c)) {
            Board b = cell.getBoard();
            if (b.containsPieceAt(c)) {
                Piece p = b.getCellAt(c).getPiece();
                p.cell = null;
                b.getStore().add(p);
            }

            cell.setPiece(null);
            cell = b.getCellAt(c);
            place();

            changePawn();

            return true;
        } else
            return false;
    }

    /**
     * Change a pawn to a queen
     *
     * @return true if you can change it and false if not
     */
    public boolean changePawn() {
        if (chessType==ChessType.WHITE_PAWN && cell.getCoordinate().getNumber()==1) {
            cell.setPiece(new WhiteQueen(cell));
            return true;
        }
        if (chessType==ChessType.BLACK_PAWN && cell.getCoordinate().getNumber()==8) {
            cell.setPiece(new WhiteQueen(cell));
            return true;
        }
        return false;
    }

    /**
     * Return a chess type from a piece
     *
     * @return the piece chess type
     */
    public ChessType getChessType() {
        return chessType;
    }

    /**
     * Return the color of the piece
     *
     * @return the color of the piece
     */
    public PieceColor getColor() {
        return chessType.getColor();
    }

    /**
     * Return the cell where the piece is located
     *
     * @return a cell
     */
    public Cell getCell() {
        return cell;
    }

    @Override
    public String toString() {
        Attribute background = cell.getColor().getAttribute();
        Attribute textColor = chessType.getColor().getAttribute();
        Attribute[] myFormat = new Attribute[]{background,textColor};

        return colorize(" " + chessType.getShape() + " ",myFormat);
    }

}
