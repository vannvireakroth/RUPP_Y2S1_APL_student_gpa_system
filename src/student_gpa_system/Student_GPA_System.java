package student_gpa_system;

import student_gpa_system.controller.StudentController;

import java.util.Scanner;

public class Student_GPA_System {

    static StudentController studentController = new StudentController();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        studentController.studentServiceIMP.seedData();

        int choice;
        do {
            printMenu();
            choice = readInt("  Choose an option: ");
            handleChoice(choice);
        } while (choice != 7);
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1 -> studentController.handleAddStudent();
            case 2 -> studentController.handleViewAllStudents();
            case 3 -> studentController.handleUpdateMarks();
            case 4 -> studentController.handleDeleteStudent();
            case 5 -> studentController.handleShowRankings();
            case 6 -> studentController.handleSearchStudent();
            case 7 -> System.out.println("\n  Goodbye!\n");
            default -> System.out.println("  ✗ Invalid option. Please choose 0–6.");
        }
    }

    private static void printMenu() {
        System.out.println("EXAM RESULT RANKING SYSTEM");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Marks");
        System.out.println("4. Delete Student");
        System.out.println("5. Show Rankings");
        System.out.println("6. Search Student");
        System.out.println("7. Exit");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Please enter a valid number.");
            }
        }
    }
}