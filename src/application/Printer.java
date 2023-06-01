package application;

import java.util.List;

public class Printer {

    public static void print(String args) {
        System.out.println(args);
    }
    public static void printMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");

        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println();
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println();
    }
    public static boolean printWord(String word, List<Character> playerGuesses) {

        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                print("-");
            }
        }

        System.out.println();
        return false;
    }
    public static void printGameBoard(char[][] gameBoard) {
        final String TRACE = "-----";
        System.out.println(TRACE);
        for (char[] line : gameBoard) {
            for (char column : line) {
                System.out.print(column);
            }
            System.out.println();
        }
        System.out.println(TRACE);
    }
}
