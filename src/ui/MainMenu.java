package ui;

import api.HotelResource;
import model.Customer;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
                    System.out.println("2. FIND A USER BY EMAIL - hijacking 2. See my reservations"); //test this out more
                    System.out.println("3. Create an account");//debug and figure out why regex isnt working
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
                            System.out.println("Enter user email: ");
                            var email = scanner.nextLine();
                            var returnedCustomer = HotelResource.getInstance().getCustomer(email);
                            System.out.println("The customer is " + returnedCustomer.toString());
                            printMainMenu();
                            break;
                        case 3:
                            //create an account

                            continueRunning = false;
                            createAccount(scanner);
                            printMainMenu();

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
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());

                    System.out.println("Error - Invalid Input. Returning to Main Menu.");
                    printMainMenu();
                }
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Enter your email address:");
        var email = scanner.nextLine();

        System.out.println("Enter your first name:");
        var firstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        var lastName = scanner.nextLine();

        HotelResource.getInstance().createACustomer(email, firstName,lastName);
    }
}