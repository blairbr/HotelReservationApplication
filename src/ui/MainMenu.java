package ui;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        printMainMenu();
    }

    public static void printMainMenu() {
        boolean continueRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (continueRunning) {
                try {
                    System.out.println("MAIN MENU - Please choose from one of the options below.");

                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");

                    String menuChoice = scanner.nextLine();
                    int menuChoiceAsInteger = Integer.parseInt(menuChoice);

                    switch (menuChoiceAsInteger) {
                        case 1:
                            continueRunning = false;
                            break;
                        case 2:
                            continueRunning = false;
                            break;
                        case 3:
                            continueRunning = false;
                            break;
                        case 4:
                            AdminMenu.printAdminMenu();
                            continueRunning = false;
                            break;
                        case 5:
                            System.out.println("Exiting the application.");
                            continueRunning = false;
                            break;
                        default:
                            break;

                    }
                } catch (Exception ex) {
                    System.out.println("Error - Invalid Input. Enter a value between 1 and 5.");
                }
            }
        }
    }
}