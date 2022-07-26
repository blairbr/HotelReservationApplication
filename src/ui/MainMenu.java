package ui;

import java.util.Scanner;

public class MainMenu {

    private AdminMenu adminMenu;

    public MainMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }

    public static void main(String[] args) {
        printMainMenu();
    }

    static void printMainMenu() {
        System.out.println("Welcome To Blair's Hotel Reservation Application. ");
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
                            menuChoice = "1";
                            continueRunning = false;
                            break;
                        case 2:
                            menuChoice = "2";
                            continueRunning = false;
                            break;
                        case 3:
                            menuChoice = "3";
                            continueRunning = false;
                            break;
                        case 4:
                            menuChoice = "4";
                            AdminMenu.printAdminMenu();
                            continueRunning = false;
                            break;
                        case 5:
                            menuChoice = "5";
                            System.out.println("Exiting the application.");
                            continueRunning = false;
                            break;
                        default:
                            menuChoice = "Invalid choice";
                            break;

                    }
                } catch (Exception ex) {
                    System.out.println("Error - Invalid Input. Enter a value between 1 and 5.");
                }
            }
        }
    }
}