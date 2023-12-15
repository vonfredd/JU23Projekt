package mainclass;

import java.util.Scanner;

public class UserInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int menuInput(int maxChoices) {
        int choice = -1;
        while (choice < 0 || choice > maxChoices) {
            choice = readIntInput();
            System.out.println("Enter valid number from menu");
        }
        return choice;
    }

    public static int readIntInput() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                return choice;
            } catch (Exception e) {
                System.out.println("Enter a number");
            }
        }
        return choice;
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }

    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to Continue...\n");
        scanner.nextLine();
    }
}
