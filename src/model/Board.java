package model;

import model.dynamicStructure.MyLinkedListStore;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nerea Mañez
 */
public class Board implements Serializable {
    private Map<Coordinate,Cell> cells;
    private IDeletedPieceManager store;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private Stack<Movement> before;
    private Stack<Movement> after;

    /**
     * Create a new board
     */
    public Board() {
        cells = new HashMap<>();
        store = new MyLinkedListStore();
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        before = new Stack<>();
        after = new Stack<>();

        Coordinate auxCoordinate;
        Cell auxCell;
        for (int row=1;row<=8;row++) {
            for (int col = 0; col < 8; col++) {
                auxCoordinate = new Coordinate((char) ('A' + col), row);
                auxCell = new Cell(this, auxCoordinate);
                cells.put(auxCoordinate, auxCell);
            }
        }

        placePieces();
    }

    /**
     * Place the pieces on the board
     */
    private void placePieces() {
        // Pawn
        for (int i=0;i<8;i++) {
            blackPieces.add(new BlackPawn(getCellAt(new Coordinate((char) ('A' + i),7))));
            whitePieces.add(new WhitePawn(getCellAt(new Coordinate((char) ('A' + i),2))));
        }

        //Rook
        blackPieces.add(new BlackRook(getCellAt(new Coordinate('A',8))));
        blackPieces.add(new BlackRook(getCellAt(new Coordinate('H',8))));
        whitePieces.add(new WhiteRook(getCellAt(new Coordinate('A',1))));
        whitePieces.add(new WhiteRook(getCellAt(new Coordinate('H',1))));

        //Bishop
        blackPieces.add(new BlackBishop(getCellAt(new Coordinate('B',8))));
        blackPieces.add(new BlackBishop(getCellAt(new Coordinate('G',8))));
        whitePieces.add(new WhiteBishop(getCellAt(new Coordinate('B',1))));
        whitePieces.add(new WhiteBishop(getCellAt(new Coordinate('G',1))));

        //Knight
        blackPieces.add(new BlackKnight(getCellAt(new Coordinate('C',8))));
        blackPieces.add(new BlackKnight(getCellAt(new Coordinate('F',8))));
        whitePieces.add(new WhiteKnight(getCellAt(new Coordinate('C',1))));
        whitePieces.add(new WhiteKnight(getCellAt(new Coordinate('F',1))));

        //Queen
        blackPieces.add(new BlackQueen(getCellAt(new Coordinate('E',8))));
        whitePieces.add(new WhiteQueen(getCellAt(new Coordinate('E',1))));

    }

    /**
     * Place the black king on the board
     *
     * @return the black king
     */
    public BlackKing placeBlackKing() {
        BlackKing bk = new BlackKing(getCellAt(new Coordinate('D',8)));
        blackPieces.add(bk);

        return bk;
    }

    /**
     * Place the white king on the board
     *
     * @return the white king
     */
    public WhiteKing placeWhiteKing() {
        WhiteKing wk = new WhiteKing(getCellAt(new Coordinate('D',1)));
        whitePieces.add(wk);

        return wk;
    }

    /**
     * Return the cells related with their coordinate
     *
     * @return a map of coordinates and cells
     */
    public Map<Coordinate,Cell> getCells() {
        return cells;
    }

    /**
     * Return a store of the pieces deleted
     *
     * @return the store
     */
    public IDeletedPieceManager getStore() {
        return store;
    }

    /**
     * Make a list of the white pieces on the board
     *
     * @return a list of the white pieces
     */
    public List<Piece> getWhitePieces() {
        List<Piece> pieces = new ArrayList<>();

        for (Cell cell : cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor()==PieceColor.WHITE) {
                pieces.add(cell.getPiece());
            }

        return pieces;
    }

    /**
     * Make a list of the black pieces on the board
     *
     * @return a list of the black pieces
     */
    public List<Piece> getBlackPieces() {
        List<Piece> pieces = new ArrayList<>();

        for (Cell cell : cells.values())
            if (cell.getPiece()!=null && cell.getPiece().getColor()==PieceColor.BLACK) {
                pieces.add(cell.getPiece());
            }

        return pieces;
    }

    /**
     * Check if a coordinate is related with a cell of the board
     *
     * @param coordinate the coordinate to check
     * @return true if the board contains a cell and false if not
     */
    public boolean containsCellAt(Coordinate coordinate){
        return cells.containsKey(coordinate);
    }

    /**
     * Highlight the cells related with the coordinates
     *
     * @param coordinates
     */
    public void hightLight(List<Coordinate> coordinates){
        for (Coordinate c : coordinates)
            getCellAt(c).hightLight();
    }

    /**
     * Returns the cell related with a coordinate
     *
     * @param coordinate
     * @return the cell
     */
    public Cell getCellAt(Coordinate coordinate){
        return cells.get(coordinate);
    }

    /**
     * Check if a coordinate contains a piece
     *
     * @param coordinate
     * @return true if contains a piece and false if not
     */
    public boolean containsPieceAt(Coordinate coordinate){
        Cell cell = getCellAt(coordinate);
        if (cell == null) return false;
        return (cell.getPiece()!=null);
    }

    /**
     * Reset the colors of the cells of the board to the original ones
     */
    public void resetColors() {
        for (Cell cell : cells.values())
            cell.resetColor();

    }

    /**
     * Count the number of pieces with this chess type
     *
     * @param chessType
     * @return the number of pieces with the chess type
     */
    public long countPiece(ChessType chessType) {

        return cells.values().stream()
                .filter(c -> !c.isEmpty())
                .filter(c -> c.getPiece().getChessType()==chessType)
                .count();
    }

    /**
     * Move a piece from one coordinate to an other one
     *
     * @param c the coordinate where the piece is placed
     * @param c2 the coordinate where the piece is going to be place
     * @return true if it can move the piece and false if not
     */
    public boolean moveTo (Coordinate c, Coordinate c2) {
        Piece p = getCellAt(c).getPiece();

        if (getCellAt(c2).getPiece()!=null)
            before.add(new Movement(c,c2));
        else
            before.add(new Movement(c,c2,getCellAt(c2).getPiece()));

        return p.moveTo(c2);
    }

    /**
     * Return a stack of movements
     *
     * @return the stack before
     */
    public Stack<Movement> getBefore () {
        return before;
    }

    /**
     * Return a stack of movements
     *
     * @return the stack after
     */
    public Stack<Movement> getAfter () {
        return after;
    }

    /**
     * Undo a movement
     */
    public void moveBack () {
        after.push(before.pop());
        moveTo(after.peek().getC2(), after.peek().getC1());
        if (after.peek().getPiece() != null)
            getCellAt(after.peek().getC2()).setPiece(after.peek().getPiece());
    }

    /**
     * Check if a king is checked
     *
     * @param king
     * @return true if is checked and false if not
     */
    public boolean isCheck (King king) {
        PieceColor pieceColor = king.getColor().next();

        Set<Coordinate> movements = cells.values().stream()
                .filter(c -> c.getPiece()!=null && c.getPiece().getColor()==(pieceColor))
                .map(c -> c.getPiece())
                .flatMap(p -> p.getNextMovements().stream())
                .collect(Collectors.toSet());

        for (Coordinate c : movements) {
            if (king.getCell().getCoordinate().equals(c))
                return true;
        }

        return false;
    }

    /**
     * Make a copy of the coard
     *
     * @return a new board
     */
    public Board copy () {
        Board bs = new Board();

        Stack<Movement> aux = new Stack<>();
        for (Movement m : before)
            aux.push(m);

        for (Movement m : aux)
            bs.moveTo(m.getC1(),m.getC2());

        return bs;
    }

    @Override
    public String toString() {
        int[] y = {1,2,3,4,5,6,7,8};
        //String output = "    A   B   C   D   E   F   G   H\n  ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n";
        String output = "   A  B  C  D  E  F  G  H\n";

        for (int row=0;row<8;row++) {
            output += y[row] + " ";
            for (int col=0;col<8;col++) {
                /*if (col==0)
                    output += y[row] + " ║" + cells[row][col];
                else if (col==7) {
                    output += "│" + cells[row][col] + "║ " + y[row] + "\n";
                    if (row!=7)
                        output += "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n";
                    else
                        output += "  ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n    A   B   C   D   E   F   G   H";
                }
                else
                    output += "│" + cells[row][col];*/
                //output += cells[row][col];
            }
            output += "\n";
        }
        return output;
    }
}
