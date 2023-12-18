package mainclass;

import classes.Course;
import classes.Student;
import crud.Create;
import crud.Delete;
import crud.Read;
import crud.Update;

import static crud.Read.studentsFromCourse;


public class Menu {

    static void create() {
        boolean isRunning = true;
        while (isRunning) {
            String createMenu = """
                    Register
                    1. Course
                    2. Student
                    3. Teacher
                    0. Back
                    """;
            System.out.println(createMenu);
            int menuChoice = UserInputHandler.menuInput(3);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> {
                    Create.course();
                    UserInputHandler.pressEnterToContinue();
                }
                case 2 -> {
                    Create.student();
                    UserInputHandler.pressEnterToContinue();
                }
                case 3 -> {
                    Create.teacher();
                    UserInputHandler.pressEnterToContinue();
                }
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
                case 1 -> {
                    Read.showCourse();
                    UserInputHandler.pressEnterToContinue();
                }
                case 2 -> {
                    readStatistics();
                    UserInputHandler.pressEnterToContinue();
                }
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
                case 1 -> {
                    Read.grades();
                    UserInputHandler.pressEnterToContinue();
                }
                case 2 -> {
                    Read.studentsFromCourse();
                    UserInputHandler.pressEnterToContinue();
                }
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
                    2. Student name
                    0. Back
                    """;
            System.out.println(updateMenu);
            int menuChoice = UserInputHandler.menuInput(1);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> {
                    Update.grades();
                    UserInputHandler.pressEnterToContinue();
                }
                case 2 -> {
                    Update.studentName();
                    UserInputHandler.pressEnterToContinue();
                }
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
            System.out.println(deleteMenu);
            int menuChoice = UserInputHandler.menuInput(3);
            switch (menuChoice) {
                case 0 -> isRunning = false;
                case 1 -> {
                    Delete.student();
                    UserInputHandler.pressEnterToContinue();
                }
                case 2 -> {
                    Delete.teacher();
                    UserInputHandler.pressEnterToContinue();
                }
                case 3 -> {
                    Delete.course();
                    UserInputHandler.pressEnterToContinue();
                }
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