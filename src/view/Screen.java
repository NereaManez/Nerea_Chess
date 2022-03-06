package view;

import com.diogonunes.jcolor.Attribute;
import model.*;

import java.util.concurrent.atomic.AtomicReference;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author Nerea Mañez
 */
public class Screen {
    /**
     * Create a new array of coordinates from an array adding a new coordinate
     *
     * @param array the inicial array
     * @param coordinate the coordinate to add
     * @return the new array of coordinates
     */
    public static Coordinate[] add(Coordinate[] array, Coordinate coordinate) {
        Coordinate[] aux = new Coordinate[array.length+1];

        for (int i= 0;i< array.length;i++) {
            aux[i] = array[i];
        }

        aux[array.length]=coordinate;
        return aux;
    }

    /**
     * Show the store
     *
     * @param store
     */
    public static void show(IDeletedPieceManager store) {
        String output = "";

        for (ChessType chessType : ChessType.values()) {
            output+=colorize(" " + chessType.getShape() + " ",Cell.CellColor.BLACK_CELL.getAttribute(),chessType.getColor().getAttribute());
        }

        output+="\n";

        for (ChessType chessType : ChessType.values()) {
            output+=colorize(" " + store.count(chessType) + " ",Cell.CellColor.WHITE_CELL.getAttribute(), Attribute.TEXT_COLOR(150,150,150));
        }

        System.out.println(output);
    }

    /**
     * Show the board with the correct view from a player's piece color
     *
     * @param board
     * @param pieceColor
     */
    public static void show(Board board, PieceColor pieceColor) {
        String output = "";

        if (pieceColor==PieceColor.WHITE) {
            output = "   A  B  C  D  E  F  G  H\n";
            Coordinate c;

            for (int row=7;row>=0;row--) {
                output += (row + 1 )+" ";
                for (int col=0;col<8;col++){
                    c = new Coordinate((char)('A'+col),row+1);
                    output += board.getCellAt(c).toString();
                }
                output += " " + (row + 1) + "\n";
            }

            output += "   A  B  C  D  E  F  G  H\n";

        } else if (pieceColor==PieceColor.BLACK){
            output ="   H  G  F  E  D  C  B  A\n";
            Coordinate c;

            for (int row = 0; row < 8; row++) {
                output += (row + 1) + " ";
                for (int col = 7; col >=0; col--) {
                    c = new Coordinate((char) ('A' + col), row + 1);
                    output += board.getCellAt(c).toString();
                }
                output += " " + (row + 1) + "\n";
            }

            output +="   H  G  F  E  D  C  B  A\n";
        }

        System.out.println(output);

        Attribute backgroundPieces = Cell.CellColor.BLACK_CELL.getAttribute();
        Attribute[] piecesFormat = new Attribute[]{};
        String ct = "";
        for (ChessType chessType : ChessType.values()) {
            piecesFormat = new Attribute[]{backgroundPieces, chessType.getColor().getAttribute()};
            ct += colorize(" " + chessType.getShape() + " ", piecesFormat);
        }

        Attribute[] counterFormat = new Attribute[] {Cell.CellColor.WHITE_CELL.getAttribute(),PieceColor.BLACK.getAttribute()};

        String piecesAlive = "", diedPieces = "";

        for (ChessType chessType : ChessType.values()) {
            piecesAlive += colorize(" " + board.countPiece(chessType) + " ", counterFormat);
            diedPieces += colorize(" " + board.getStore().count(chessType) + " ",counterFormat);
        }

        System.out.println(ct + "\n" + piecesAlive);
        System.out.println();
        System.out.println(ct + "\n" + diedPieces);
    }
}
