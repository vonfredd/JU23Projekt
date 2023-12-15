package mainclass;

import classes.Course;
import crud.Create;
import crud.Read;

import static crud.Read.studentsFromCourse;


public class Menu {

    static void create() {
        boolean isRunning = true;
        while (isRunning) {
            String createMenu = """
                    Register
                    1. Course
                    2. Student
                    0. Back
                    """;
            System.out.println(createMenu);
            int menuChoice = UserInputHandler.menuInput(2);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> Create.course();
                case 2 -> Create.student();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void read() {
        boolean isRunning = true;
        while (isRunning) {
            String readMenu = """
                    Show
                    1. Course
                    2. Statistics
                    0. Back
                    """;
            System.out.println(readMenu);
            int menuChoice = UserInputHandler.menuInput(2);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> Read.showCourse();
                case 2 -> readStatistics();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void readStatistics() {
        boolean isRunning = true;
        while (isRunning) {
            String readStatisticsMenu = """
                    Statistics
                    1. Student grades
                    2. Students in each class
                    0. Back
                    """;

            System.out.println(readStatisticsMenu);
            int menuChoice = UserInputHandler.menuInput(2);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> Read.grades();
                case 2 -> Read.studentsFromCourse();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void update() {
        boolean isRunning = true;
        while (isRunning) {
            String updateMenu = """
                    Update
                    1. Grades
                    0. Back
                    """;
            int menuChoice = UserInputHandler.menuInput(1);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                // case 1 -> Grades
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void delete() {
        boolean isRunning = true;
        while (isRunning) {
            String deleteMenu = """
                    Remove
                    1. Student
                    2. Teacher
                    3. Course
                    0. Back
                    """;
            int menuChoice = UserInputHandler.menuInput(3);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                // case 1 -> Course
                // case 2 -> Student
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void showMain(){
        String mainMenu="""
                    Main menu
                    1. Register course, student etc.
                    2. Show statistics, grades, etc.
                    3. Update existing data
                    4. Remove
                    0. Quit
                    """;
        System.out.println(mainMenu);
    }
}