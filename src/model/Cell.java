package model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author Nerea Mañez
 */
public class Cell implements Serializable {
    private Coordinate coordinate;
    private CellColor color;
    private CellColor originalColor;
    private Piece piece;
    private Board board;

    /**
     * Create a new cell
     *
     * @param board the board whose going to contain the cell
     * @param coordinate the coordinate that represent the cell
     */
    public Cell(Board board,Coordinate coordinate) {
        this.board = board;
        this.coordinate = coordinate;
        if (((coordinate.getLetter()-'A')+ coordinate.getNumber())%2==0)
            originalColor = CellColor.BLACK_CELL;
        else
            originalColor = CellColor.WHITE_CELL;

        color = originalColor;
    }

    /**
     * Return the actual color of the cell
     *
     * @return the actual cell
     */
    public CellColor getColor(){
        return color;
    }

    /**
     * Return the piece located on the cell
     *
     * @return a piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Check if a piece is on the cell
     *
     * @return true if the cell has a piece and false if not
     */
    public boolean isEmpty() {
        return getPiece()==null;
    }

    /**
     * Return the board that contains the cell
     *
     * @return a board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Return the coordinate related with the cell
     *
     * @return a coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Place a piece on the cell
     *
     * @param piece
     */
    public void setPiece(Piece piece){
        this.piece = piece;
    }

    /**
     * Change the color from the highlight one
     */
    public void hightLight() {
        if (piece!=null) {
            if (originalColor == CellColor.BLACK_CELL)
                color = CellColor.HIGHTLIGHT_KILL_BLACK;
            else
                color = CellColor.HIGHTLIGHT_KILL_WHITE;
        } else {
            if (originalColor == CellColor.BLACK_CELL)
                color = CellColor.HIGHTLIGHT_BLACK;
            else
                color = CellColor.HIGHTLIGHT_WHITE;
        }
    }

    /**
     * Reset the color from the original one
     */
    public void resetColor() {
        color = originalColor;
    }

    @Override
    public String toString() {
        Attribute[] myFormat = new Attribute[]{color.getAttribute()};
        if (piece!=null)
            return piece.toString();
        else
            return colorize("   ",myFormat);
    }

    /**
     * The posible colors of the cells
     */
    public enum CellColor {
        WHITE_CELL(Attribute.BACK_COLOR(180,180,180)),
        BLACK_CELL(Attribute.BACK_COLOR(100,100,100)),
        HIGHTLIGHT_WHITE(Attribute.BACK_COLOR(250,247,107)),
        HIGHTLIGHT_BLACK(Attribute.BACK_COLOR(208,205,28)),
        HIGHTLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180,0,0)),
        HIGHTLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130,0,0));

        private Attribute color;
        CellColor(Attribute color) {
            this.color = color;
        }

        public Attribute getAttribute() {
            return color;
        }
    }
}
