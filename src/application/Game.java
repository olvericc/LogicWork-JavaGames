package application;

import java.util.*;

public class Game {
    public static class Game1 {
        static ArrayList<Integer> p1Positions = new ArrayList<>();
        static ArrayList<Integer> p2Positions = new ArrayList<>();

        public static char[][] gameBoard() {
            return new char[][]{
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
            };
        }

        public static void placePiece(char[][] gameBoard, int position, String userTurn) {
            char character = ' ';

            if (userTurn.equals("p1")) {
                character = 'x';
                p1Positions.add(position);
            } else if (userTurn.equals("p2")) {
                character = 'o';
                p2Positions.add(position);
            }

            switch (position) {
                case 1 -> gameBoard[0][0] = character;
                case 2 -> gameBoard[0][2] = character;
                case 3 -> gameBoard[0][4] = character;
                case 4 -> gameBoard[2][0] = character;
                case 5 -> gameBoard[2][2] = character;
                case 6 -> gameBoard[2][4] = character;
                case 7 -> gameBoard[4][0] = character;
                case 8 -> gameBoard[4][2] = character;
                case 9 -> gameBoard[4][4] = character;
                default -> {
                }
            }
        }

        public static String logicForWinning() {
            List<Integer> lineOne = Arrays.asList(1, 2, 3);
            List<Integer> lineTwo = Arrays.asList(4, 5, 6);
            List<Integer> lineThree = Arrays.asList(7, 8, 9);
            List<Integer> columnOne = Arrays.asList(1, 4, 7);
            List<Integer> columnTwo = Arrays.asList(2, 5, 8);
            List<Integer> columnThree = Arrays.asList(3, 6, 9);
            List<Integer> diagonalOne = Arrays.asList(1, 5, 9);
            List<Integer> diagonalTwo = Arrays.asList(7, 5, 3);

            List<List<Integer>> win = new ArrayList<>();
            win.add(lineOne);
            win.add(lineTwo);
            win.add(lineThree);
            win.add(columnOne);
            win.add(columnTwo);
            win.add(columnThree);
            win.add(diagonalOne);
            win.add(diagonalTwo);

            for (List<Integer> list : win) {
                if (p1Positions.containsAll(list)) {
                    return "player 1 wins!";
                } else if (p2Positions.containsAll(list)) {
                    return "player 2 wins!";
                } else if (p1Positions.size() + p2Positions.size() == 9) {
                    return "tied game!";
                }
            }
            return "";
        }
    }
    public static class Game2 {
        private String word;
        private List<Character> guesses;
        private int wrongCount;

        public void singlePlayer() {
            String[] words = {"popcorn", "apple", "avocado", "grape", "orange", "watermelon"};
            List<String> hiddenWords = new ArrayList<>(Arrays.asList(words));
            Random random = new Random();

            word = hiddenWords.get(random.nextInt(hiddenWords.size()));
            guesses = new ArrayList<>();
            wrongCount = 0;
        }

        public void playing(Scanner sc) {
            while (true) {
                Printer.printMan(wrongCount);

                if (wrongCount >= 6) {
                    System.out.println("you lose!");
                    System.out.println("the word was: " + word);
                    break;
                }

                Printer.printWord(word, guesses);
                if (!guesses(sc, word, guesses)) {
                    wrongCount++;
                }

                if (Printer.printWord(word, guesses)) {
                    System.out.println("you win!");
                    break;
                }

                System.out.println("please enter your guess for the word:");
                if (sc.nextLine().equals(word)) {
                    System.out.println("you win!");
                    break;
                } else {
                    System.out.println("miss! try again.");
                }
            }
        }

        private boolean guesses(Scanner sc, String word, List<Character> playerGuesses) {
            System.out.println("please enter a letter:");
            String letterGuess = sc.nextLine();
            playerGuesses.add(letterGuess.charAt(0));

            return word.contains(letterGuess);
        }

    }
    public static class Game3 {
        public boolean isValidMove(int[][] matrix, int row, int column, int number) {
            for (int i = 0; i < 9; i++) {
                if (matrix[row][i] == number || matrix[i][column] == number) {
                    return false;
                }
            }

            int blockRow = row / 3 * 3;
            int blockColumn = column / 3 * 3;
            for (int i = blockRow; i < blockRow + 3; i++) {
                for (int j = blockColumn; j < blockColumn + 3; j++) {
                    if (matrix[i][j] == number) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isSudokuComplete(int[][] matrix) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (matrix[i][j] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void insertNumber(int[][] matrix, int row, int column, int number) {
            matrix[row][column] = number;
        }
    }
}