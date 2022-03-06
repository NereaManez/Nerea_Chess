package view;

import model.Coordinate;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author Nerea Mañez
 */
public class Input {
    static String red_bright = "\033[0;91m";
    static String cyan = "\033[36m";
    static String reset= "\u001B[0m";

    /**
     * Ask for a new game or load an existing one
     *
     * @return n for a new game an l for load one
     */
    public static char newGame() {
        Scanner sc = new Scanner(System.in);
        String optionS = "";
        char option;
        boolean bOption = false;

        while (!bOption) {
            System.out.println(cyan + "Do u want to create a new game [N] or load it [L]? " + reset);
            optionS = sc.next();
            if (optionS.equals("") || (optionS.toUpperCase().charAt(0) != 'N' && optionS.toUpperCase().charAt(0) != 'L'))
                System.out.println(red_bright + "Invalid option." + reset);
            else
                bOption = true;
        }

        option = optionS.toUpperCase().charAt(0);

        return option;
    }

    /**
     * Ask the name of a player
     *
     * @return a string, the name
     */
    public static String playerName() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        boolean bName = false;

        while (!bName) {
            System.out.println(cyan + "Enter a name : " + reset);
            name = sc.next();
            if (name.equals(""))
                System.out.println(red_bright + "Name can't be null" + reset);
            else
                bName = true;
        }

        return name;
    }

    /**
     * Ask for a coordinate
     *
     * @return the coordinate
     * @version v2
     */
    public static Coordinate readCoordinate2() {
        Scanner sc = new Scanner(System.in);
        String coord;
        char letter = 0, num;
        int number = 0;
        boolean bCoord = false;

        while (!bCoord) {
            System.out.println(cyan + "Write a coordinate. Example -> A2" + reset);
            coord = sc.next().toUpperCase(Locale.ROOT);
            if (coord.length() == 2) {
                letter = coord.charAt(0);
                num = coord.charAt(1);

                if ((letter < 'A' || letter > 'H') && letter != 'J')
                    System.out.println(red_bright + "Incorrect letter, enter a letter between A and H (both included)" + reset);
                else {
                    if (letter == 'J' && num == '1')
                        bCoord = true;
                    else {
                        if (num > '0' && num < '9') {
                            number = Integer.parseInt(String.valueOf(num + ""));
                            bCoord = true;
                        } else {
                            System.out.println(red_bright + "Incorrect number, enter a number between 1 and 8 (both included)" + reset);
                        }
                    }
                }
            } else
                System.out.println(red_bright + "Remember to enter a coordinate like [A-H][1-8]" + reset);
        }

        return new Coordinate(letter,number);
    }

    /**
     * Ask for a coordinate
     *
     * @return the coordinate
     * @version v1
     * @deprecated
     */
    public static Coordinate readCoordinate() {
        Scanner sc = new Scanner(System.in);
        char letter = 0, num;
        int number = 0;
        boolean bLetter = false, bNumber = false;

        while (!bLetter) {
            System.out.println(cyan + "Select a letter : " + reset);
            letter = sc.next().toUpperCase(Locale.ROOT).charAt(0);
            if (letter==0)
                System.out.println(red_bright + "Letter can't be null" + reset);
            else {
                if (letter < 'A' || letter > 'H')
                    System.out.println(red_bright + "Incorrect letter, enter a letter between A and H (both included)" + reset);
                else
                    bLetter = true;
            }
        }

        while (!bNumber) {
            System.out.println(cyan + "Select a number : ");
            num = sc.next().charAt(0);
            if (num==0)
                System.out.println(red_bright + "Number can't be null");
            else {
                if (num > '0' && num < '9') {
                    number = Integer.parseInt(String.valueOf(num + ""));
                    bNumber = true;
                } else {
                    System.out.println(red_bright + "Incorrect number, enter a number between 1 and 8 (both included)");
                }
            }
        }

        return new Coordinate(letter,number);
    }
}
