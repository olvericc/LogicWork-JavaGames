package application;

import java.util.Random;
import java.util.Scanner;

public class Executer {
    public static final int GAME_HANGMAN = 1;
    public static final int GAME_TICTACTOE = 2;
    public static final int GAME_SUDOKU = 3;
    public static final int EXIT = 4;
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
                case GAME_SUDOKU -> playGame3(sc);
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
        System.out.println("3. sudoku");
        System.out.println("4. exit");
        System.out.print("option: ");
    }
    public static void playGame1(Scanner sc1) {
        System.out.println("====== HANGMAN GAME ======");
        Game.Game2 game = new Game.Game2();
        game.singlePlayer();

        game.playing(sc1);
    }

    public static void playGame2(Scanner sc2) {
        System.out.println("====== TIC TAC TOE GAME ======");
        char[][] gameBoard = Game.Game1.gameBoard();
        Printer.printGameBoard(gameBoard);

        while (true) {
            System.out.print("enter a number between 1 and 9:");
            int p1Position = sc2.nextInt();

            while (Game.Game1.p1Positions.contains(p1Position) || Game.Game1.p2Positions.contains(p1Position)) {
                System.out.print("try again, enter a valid position:");
                p1Position = sc2.nextInt();
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
    public static void playGame3(Scanner sc3) {
        System.out.println("====== SUDOKU GAME ======");

        int[][] matrix = {
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 8, 0, 0, 4, 7},
                {0, 5, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 6, 0, 7, 0, 5, 0, 0},
                {0, 7, 0, 0, 0, 0, 0, 9, 0},
                {9, 6, 0, 0, 1, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        Game.Game3 game = new Game.Game3();

        Printer.printTable(matrix);

        while (!game.isSudokuComplete(matrix)) {
            Printer.print("Enter the row (0 to 8): ");
            int row = sc3.nextInt();
            Printer.print("Enter the column (0 to 8): ");
            int column = sc3.nextInt();
            Printer.print("Enter the number (1 to 9): ");
            int number = sc3.nextInt();

            if (game.isValidMove(matrix, row, column, number)) {
                game.insertNumber(matrix, row, column, number);
                Printer.println("Number inserted successfully!");
            } else {
                Printer.println("Wrong move! Try again.");
            }
            Printer.println("");
            Printer.printTable(matrix);
        }
        Printer.println("Congratulations! You completed the Sudoku!");
    }
}
