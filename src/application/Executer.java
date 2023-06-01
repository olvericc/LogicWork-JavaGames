package application;

import java.util.Random;
import java.util.Scanner;

public class Executer {

    public static final int GAME_HANGMAN = 1;
    public static final int GAME_TICTACTOE = 2;
    public static final int EXIT = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMainMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case GAME_HANGMAN -> playGame1(sc);
                case GAME_TICTACTOE -> playGame2(sc);
                case EXIT -> System.out.println("closing the game...");
                default -> System.out.println("invalid option! try again.");
            }

            System.out.println();
        } while (choice != EXIT);
    }
    public static void printMainMenu() {
        System.out.println("====== game menu ======");
        System.out.println("select a game with a number:");
        System.out.println("1. hangman");
        System.out.println("2. tic tac toe");
        System.out.println("3. exit");
        System.out.print("option: ");
    }
    public static void playGame1(Scanner sc) {
        System.out.println("====== TIC TAC TOE GAME ======");
        char[][] gameBoard = Game.Game1.gameBoard();
        Printer.printGameBoard(gameBoard);

        while (true) {
            System.out.print("enter a number between 1 and 9:");
            int p1Position = sc.nextInt();

            while (Game.Game1.p1Positions.contains(p1Position) || Game.Game1.p2Positions.contains(p1Position)) {
                System.out.print("try again, enter a valid position:");
                p1Position = sc.nextInt();
            }

            Game.Game1.placePiece(gameBoard, p1Position, "p1");
            Printer.printGameBoard(gameBoard);

            String gameResult = Game.Game1.logicForWinning();
            if (!gameResult.isEmpty()) {
                System.out.println(gameResult);
                break;
            }

            Random p2Turn = new Random();
            int p2Position;

            do {
                p2Position = p2Turn.nextInt(9) + 1;
            } while (Game.Game1.p1Positions.contains(p2Position) || Game.Game1.p2Positions.contains(p2Position));

            Game.Game1.placePiece(gameBoard, p2Position, "p2");
            Printer.printGameBoard(gameBoard);

            gameResult = Game.Game1.logicForWinning();
            if (!gameResult.isEmpty()) {
                System.out.println(gameResult);
                break;
            }
        }
    }
    public static void playGame2(Scanner sc) {
        System.out.println("====== HANGMAN GAME ======");
        Game.Game2 game = new Game.Game2();
        game.singlePlayer();

        game.playing(sc);
    }
}
