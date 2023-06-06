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
                case EXIT -> System.out.println("Fechando o jogo...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

            System.out.println();
        } while (choice != EXIT);
    }
    public static void printMainMenu() {
        System.out.println("====== GAME MENU ======");
        System.out.println("Selecione o jogo com um número:");
        System.out.println("1. jogo da forca");
        System.out.println("2. jogo da velha");
        System.out.println("3. sudoku");
        System.out.println("4. sair");
        System.out.println("opção: ");
    }
    public static void playGame1(Scanner sc1) {
        System.out.println("====== JOGO DA FORCA ======");
        Game.Game2 game = new Game.Game2();
        game.singlePlayer();

        game.playing(sc1);
    }
    public static void playGame2(Scanner sc2) {
        System.out.println("====== JOGO DA VELHA ======");
        char[][] gameBoard = Game.Game1.gameBoard();

        Printer.printGameBoard(gameBoard);

        while (true) {
            System.out.print("Digite um número entre 1 e 9:");
            int p1Position = sc2.nextInt();

            while (Game.Game1.p1Positions.contains(p1Position) || Game.Game1.p2Positions.contains(p1Position)) {
                System.out.print("Tente novamente! Digite uma posição válida:");
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
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
                {0, 0, 0, 0, 4, 0, 0, 7, 3},
                {4, 3, 8, 6, 0, 7, 0, 5, 9},
                {5, 0, 6, 9, 3, 1, 0, 2, 8},
                {2, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 2, 0, 6, 0, 0, 5},
                {0, 6, 4, 1, 5, 0, 0, 0, 0},
                {0, 2, 3, 0, 0, 9, 5, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 6},
                {6, 0, 7, 0, 0, 0, 9, 0, 4}
        };

        Game.Game3 game = new Game.Game3();

        Printer.printTable(matrix);

        while (!game.isSudokuComplete(matrix)) {
            Printer.print("Digite a linha (0 a 8): ");
            int row = sc3.nextInt();
            Printer.print("Digite a coluna (0 a 8): ");
            int column = sc3.nextInt();
            Printer.print("Digite o número (1 a 9): ");
            int number = sc3.nextInt();

            if (game.isValidMove(matrix, row, column, number)) {
                game.insertNumber(matrix, row, column, number);
                Printer.println("Número inserido com sucesso!");
            } else {
                Printer.println("Movimento errado! Tente novamente.");
            }
            Printer.println("");
            Printer.printTable(matrix);
        }
        Printer.println("Parabéns! Você completou o Sudoku");
    }
}
