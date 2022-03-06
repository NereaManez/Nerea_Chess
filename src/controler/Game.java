package controler;

import model.*;
import view.*;
import view.animations.Intro;
import view.animations.Victoria_PvP;

import java.io.*;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * Game structure
 *
 * @author Nerea Mañez
 */
public class Game implements Serializable {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private PieceColor turn;
    private WhiteKing whiteKing;
    private BlackKing blackKing;

    String red_bright = "\033[0;91m";
    String reset= "\u001B[0m";

    /**
     * Create a game and start playing
     */
    public Game(){
        Intro.into_animation();

        if (Input.newGame()=='N') {

            whitePlayer = new Player(Input.playerName());
            blackPlayer = new Player(Input.playerName());

            board = new Board();
            whiteKing = board.placeWhiteKing();
            blackKing = board.placeBlackKing();

            turn = PieceColor.WHITE;

            start();
        } else {
            load();
        }
    }

    /**
     * Starts the game and play
     */
    private void start() {
        boolean continuePlaying = true;

        do {
            continuePlaying = move();

        } while (continuePlaying);

        if (whiteKing.getCell() == null) {
            Screen.show(board, turn.next());
            Victoria_PvP.j2();
        }
        else if (blackKing.getCell() == null) {
            Screen.show(board, turn.next());
            Victoria_PvP.j1();
        } else {
            Screen.show(board,turn.next());
            System.out.println(red_bright + "Check mate has been done"+ reset);
            if (turn==PieceColor.WHITE)
                Victoria_PvP.j1();
            else
                Victoria_PvP.j2();
        }
    }

    /**
     * Make the move of one piece in each round and check if the game is over or not
     *
     * @return if the game is over return false, if not return true
     */
    private boolean move () {
        Coordinate pieceCoordinate, moveToCoordinate;
        Piece piece;
        List<Coordinate> move;
        boolean isCheck = true, haveMoves = false, canMoveTo = false;

        if (whiteKing.getCell() != null && blackKing.getCell() != null && !isCheckMate()) {
            do {
                try {
                    sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                limpiaPantalla();

                tellTurn();
                Screen.show(board, turn);

                haveMoves = false;
                do {
                    do {
                        pieceCoordinate = Input.readCoordinate2();
                        if (pieceCoordinate.getLetter() == 'J' && pieceCoordinate.getNumber() == 1) {
                            save();
                            System.exit(0);
                        }
                        piece = board.getCellAt(pieceCoordinate).getPiece();
                    } while (!board.containsCellAt(pieceCoordinate) || !board.containsPieceAt(pieceCoordinate) || piece.getColor() != turn);

                    move = piece.getNextMovements();

                    if (move.isEmpty())
                        System.out.println(red_bright + "This piece has no possible moves" + reset);
                    else
                        haveMoves = true;
                } while (!haveMoves);
                board.hightLight(move);

                try {
                    sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                limpiaPantalla();

                tellTurn();
                Screen.show(board, turn);

                canMoveTo = false;
                do {
                    moveToCoordinate = Input.readCoordinate2();

                    if (!board.containsCellAt(moveToCoordinate) || !board.moveTo(pieceCoordinate, moveToCoordinate))
                        System.out.println(red_bright + "The coordinate is invalid." + reset);
                    else
                        canMoveTo = true;
                } while (!canMoveTo);

                if (turn == PieceColor.WHITE) {
                    if (!board.isCheck(whiteKing))
                        isCheck = false;
                    else {
                        System.out.println(red_bright + "Your king is still in check, make a valid move." + reset);
                        board.moveBack();
                    }
                } else {
                    if (!board.isCheck(blackKing))
                        isCheck = false;
                    else {
                        System.out.println(red_bright + "Your king is still in check, make a valid move." + reset);
                        board.moveBack();
                    }
                }

                board.resetColors();

            } while (isCheck);

            turn = turn.next();

            return true;
        } else {
            return false;
        }
    }

    /**
     * Show whose turn is it in each round
     */
    private void tellTurn() {
        if (turn==PieceColor.WHITE)
            System.out.println(whitePlayer.getName() + "'s turn -> WHITE\n");
        else
            System.out.println(blackPlayer.getName() + "'s turn -> BLACK\n");
    }

    /**
     * Check if there is checkmate
     *
     * @return true if is checkmate and false if not
     */
    private boolean isCheckMate () {
        Board bs = board.copy();

        List<Piece> pieces = bs.getCells().values().stream()
                .filter(c -> c.getPiece()!=null && c.getPiece().getColor()==(turn))
                .map(c -> c.getPiece())
                .collect(Collectors.toList());

        for (Piece p : pieces) {
            for (Coordinate c : p.getNextMovements()) {
                bs.moveTo(p.getCell().getCoordinate(), c);
                if (turn == PieceColor.WHITE) {
                    if (bs.isCheck(whiteKing))
                        bs.moveBack();
                    else
                        return false;
                } else {
                    if (bs.isCheck(blackKing))
                        bs.moveBack();
                    else
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Save the game on a file
     */
    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("game_file")))) {
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load th game from a file
     */
    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("game_file")))) {
            Game g = (Game) ois.readObject();
            this.board = g.board;
            this.whitePlayer = g.whitePlayer;
            this.whiteKing = g.whiteKing;
            this.blackPlayer = g.blackPlayer;
            this.blackKing = g.blackKing;
            this.turn = g.turn;

            start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(red_bright + "INPUT ERROR" + reset);
            System.exit(0);
        }
    }

    /**
     * Cleans the screen, is equivalent to Ctrl+L in the powershell
     */
    private static void limpiaPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
