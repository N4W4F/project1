import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Welcome to the TicTacToe game with Java!");

            // Ask user to choose either 1 round or 3 rounds
            int rounds = getNumberOfRounds();

            // Ask user to choose his rule 'X' or 'O'
            String userRole = getUserRole();

            // Choosing the opposite rule for the CPU
            String cpuRole = userRole.equalsIgnoreCase("X") ? "O" : "X";

            int userWins = 0, cpuWins = 0;
            int maxWins = (rounds == 3) ? 2 : 1;

            for (int round = 1; round <= rounds && userWins < maxWins && cpuWins < maxWins; round++) {
                System.out.println("\nStarting Round " + round);
                String[][] board = {
                        { "1", "2", "3" },
                        { "4", "5", "6" },
                        { "7", "8", "9" }
                };

                String result = playRound(board, userRole, cpuRole);
                if (result.equals("User")) {
                    userWins++;
                    System.out.println("You won Round " + round + "!");
                } else if (result.equals("CPU")) {
                    cpuWins++;
                    System.out.println("CPU won Round " + round + "!");
                } else {
                    System.out.println("Round " + round + " was a draw.");
                }
            }

            // Final result based on wins
            if (userWins > cpuWins) {
                System.out.println("\nCongrats! You won the game with " + userWins + " wins.");
            } else if (cpuWins > userWins) {
                System.out.println("\nCPU won the game with " + cpuWins + " wins. Better luck next time!");
            } else {
                System.out.println("\nThe game was a draw.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void showBoard(String[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print("\t| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 2)
                    System.out.print("   ");
            }
            System.out.println(" |");
        }
        System.out.println();
    }

    public static String playRound(String[][] board, String userRole, String cpuRole) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean userWon = false, cpuWon = false;
        int turns = 0;

        do {
            showBoard(board);
            int position = getUserPosition(board);
            updateBoard(board, position, userRole);
            turns++;

            // Check if user won after their move
            userWon = isWon(board, userRole);
            if (userWon || turns >= 9)
                break;

            // CPU turn
            int cpuPosition = getRandomEmptyPosition(board);
            updateBoard(board, cpuPosition, cpuRole);
            turns++;

            // Check if CPU won after its move
            cpuWon = isWon(board, cpuRole);
        } while (!userWon && !cpuWon && turns < 9);

        showBoard(board);
        if (userWon)
            return "User";
        else if (cpuWon)
            return "CPU";
        else
            return "Draw";
    }

    // Bonus question
    public static int getNumberOfRounds() {
        Scanner input = new Scanner(System.in);
        int rounds = 0;
        do {
            System.out.print("Would you like to play 1 or 3 rounds? Enter 1 or 3: ");
            try {
                rounds = input.nextInt();
                if (rounds != 1 && rounds != 3) {
                    System.out.println("Invalid choice! Please enter either 1 or 3: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number (1 or 3): ");
                input.next(); // Clear invalid input
            }
        } while (rounds != 1 && rounds != 3);
        return rounds;
    }

    public static String getUserRole() {
        Scanner input = new Scanner(System.in);

        String role = "";
        do {
            System.out.print("Please, choose 'X' or 'O' to start the game: ");
            role = input.next();

            if (!role.equalsIgnoreCase("X") && !role.equalsIgnoreCase("O"))
                System.out.println("Invalid input! Please enter either 'X' or 'O'.");

        } while (!role.equalsIgnoreCase("X") && !role.equalsIgnoreCase("O"));
        return role;
    }

    public static int getUserPosition(String[][] board) {
        Scanner input = new Scanner(System.in);
        int position = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please, enter your next position from 1 to 9: ");
            try {
                position = input.nextInt();
                if (position < 1 || position > 9)
                    System.out.println("Invalid position! Choose a number between 1 and 9.");

                else if (!isEmptyPosition(board, position))
                    System.out.println("Position is taken! Try another position.");

                else
                    validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Choose a number between 1 and 9.");
                input.next(); // Clear invalid input
            }
        }
        return position;
    }

    public static int getRandomEmptyPosition(String[][] board) {
        Random rand = new Random();
        int position;
        do {
            position = 1 + rand.nextInt(9);
        } while (!isEmptyPosition(board, position));
        return position;
    }

    public static boolean isEmptyPosition(String[][] board, int position) {
        switch (position) {
            case 1: return board[0][0].equals("1");
            case 2: return board[0][1].equals("2");
            case 3: return board[0][2].equals("3");
            case 4: return board[1][0].equals("4");
            case 5: return board[1][1].equals("5");
            case 6: return board[1][2].equals("6");
            case 7: return board[2][0].equals("7");
            case 8: return board[2][1].equals("8");
            case 9: return board[2][2].equals("9");
        }
        return false;
    }

    public static void updateBoard(String[][] board, int position, String role) {
        switch (position) {
            case 1: board[0][0] = role; break;
            case 2: board[0][1] = role; break;
            case 3: board[0][2] = role; break;
            case 4: board[1][0] = role; break;
            case 5: board[1][1] = role; break;
            case 6: board[1][2] = role; break;
            case 7: board[2][0] = role; break;
            case 8: board[2][1] = role; break;
            case 9: board[2][2] = role; break;
        }
    }

    public static boolean isWon(String[][] board, String player) {
        for (int i = 0; i < board.length; i++) {
            // Check rows
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player))
                return true;

            // Check columns
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))
                return true;
        }
        // Check diagonals
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            return true;
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            return true;
        return false;
    }
}
