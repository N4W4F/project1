import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> results = new ArrayList<>();
        double lastResult = 0;
        int choice;

        System.out.println("Welcome to the Java Calculator!");
        System.out.print("Enter the first number: ");
        double num1 = input.nextDouble();
        System.out.print("Enter the second number: ");
        double num2 = input.nextDouble();

        while (true) {
            displayMenu();
            try {
                choice = input.nextInt();

                if (choice == -1) {
                    System.out.println("\nExiting the calculator. Thank you!");
                    break;
                }

                if (choice == 9) {
                    printLastResult(lastResult);
                    continue;
                }

                if (choice == 10) {
                    printAllResults(results);
                    continue;
                }

                lastResult = doOperation(choice, num1, num2);
                results.add(lastResult);
                System.out.println("\nResult: " + lastResult);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next(); // Clear the invalid input
            }
        }
        input.close();
    }

    public static void displayMenu() {
        System.out.println("\nEnter 1 to addition the numbers\n" +
                "Enter 2 to substraction the numbers\n" +
                "Enter 3 to multiplication the numbers\n" +
                "Enter 4 to division the numbers\n" +
                "Enter 5 to modulus the numbers\n" +
                "Enter 6 to find minimum number\n" +
                "Enter 7 to find maximum number\n" +
                "Enter 8 to find the average of numbers\n" +
                "Enter 9 to print the last result in calculator\n" +
                "Enter 10 to print the list of all results in calculator\n" +
                "Enter -1 to quit");
    }

    public static double doOperation(int choice, double num1, double num2) {
        switch (choice) {
            case 1: return add(num1, num2);
            case 2: return subtract(num1, num2);
            case 3: return multiply(num1, num2);
            case 4: return divide(num1, num2);
            case 5: return modulus(num1, num2);
            case 6: return min(num1, num2);
            case 7: return max(num1, num2);
            case 8: return average(num1, num2);
            default:
                System.out.println("Invalid choice, please try again.");
                return 0;
        }
    }

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        try {
            if (num2 == 0)
                throw new ArithmeticException("Error: Division by zero is not allowed.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return num1 / num2;
    }

    public static double modulus(double num1, double num2) {
        try {
            if (num2 == 0)
                throw new ArithmeticException("Error: Modulus by zero is not allowed.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return num1 % num2;
    }

    public static double min(double num1, double num2) {
        return (num1 < num2) ? num1 : num2;
    }

    public static double max(double num1, double num2) {
        return (num1 > num2) ? num1 : num2;
    }

    public static double average(double num1, double num2) {
        return (num1 + num2) / 2;
    }

    public static void printLastResult(double lastResult) {
        System.out.println("Last result: " + lastResult);
    }

    public static void printAllResults(ArrayList<Double> results) {
        System.out.println("All results: " + results);
    }
}
