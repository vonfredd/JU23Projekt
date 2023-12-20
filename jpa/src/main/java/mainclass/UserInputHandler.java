package mainclass;

import java.util.Scanner;

public class UserInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int menuInput(int maxChoices) {
        int choice = readIntInput();
        while (choice < 0 || choice > maxChoices) {
            System.out.println("You have to enter a number between 0 and " + maxChoices);
            choice = readIntInput();
        }
        return choice;
    }

    public static int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You have to enter a number: ");
            }
        }
    }

    public static String readStringInput() {
        return scanner.nextLine();
    }

    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to Continue...\n");
        scanner.nextLine();
    }
}
