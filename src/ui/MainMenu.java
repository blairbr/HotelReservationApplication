package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;
import service.InvalidCheckInDatesException;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    System.out.println("==== MAIN MENU ==== Please choose from one of the options below.");

                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservations");
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");

                    String menuChoice = scanner.nextLine();
                    int menuChoiceAsInteger = Integer.parseInt(menuChoice);

                    switch (menuChoiceAsInteger) {
                        case 1:
                            reserveRoom(scanner);
                            continueRunning = false;
                            break;
                        case 2:
                            continueRunning = false;
                            displayUserReservations(scanner);
                            printMainMenu();
                            break;
                        case 3:
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
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());

                    System.out.println("Error - Invalid Input. Returning to Main Menu.");
                    printMainMenu();
                }
            }
        }
    }

    private static void displayUserReservations(Scanner scanner) {
        System.out.println("Enter user email: ");
        var email = scanner.nextLine();
        var returnedCustomer = HotelResource.getInstance().getCustomer(email);
        var listOfReservations = HotelResource.getInstance().getCustomersReservations(email);
        if (listOfReservations.isEmpty()) {
            System.out.println("No reservations were found for " + returnedCustomer.getFirstName() + " " +  returnedCustomer.getLastName() + ".");
            }
        for (Reservation reservation : listOfReservations) {
            System.out.println(reservation.toString());
        }
    }


    private static void reserveRoom(Scanner scanner) throws ParseException {
        System.out.println("Enter Check In date - mm/dd/yyyy - example: 07/25/2023");
        var checkInDateString = scanner.nextLine();

        System.out.println("Enter Check Out date - mm/dd/yyyy - example: 07/30/2023");
        var checkOutDateString = scanner.nextLine();
        Date checkInDate= new SimpleDateFormat("M/d/yyyy").parse(checkInDateString);
        Date checkOutDate =new SimpleDateFormat("M/d/yyyy").parse(checkOutDateString);

        try {
            if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate))
            {
                throw new InvalidCheckInDatesException("Check out date must be after the check in date.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            reserveRoom(scanner);
        }

        var availableRooms = HotelResource.getInstance().findARoom(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms are available between " + checkInDateString + " and " + checkOutDateString);
            System.out.println("Returning to the main menu");
            printMainMenu();
        }
        System.out.println("--Available Rooms: --");
        for (IRoom availableRoom: availableRooms) {
            System.out.println(availableRoom.toString());
        }

        System.out.println("Do you have an account with us? Y or N");
        var userResponse = scanner.nextLine();
        if (userResponse.equalsIgnoreCase("Y") || userResponse.equalsIgnoreCase("Yes")) {
            System.out.println("Enter your email address:");
            var email = scanner.nextLine();
            var customer = HotelResource.getInstance().getCustomer(email);
            System.out.println("Welcome " + customer.getFirstName());
            System.out.println("What room would you like to reserve?");
            var roomNumberToReserve = scanner.nextLine();
            var room = ReservationService.getInstance().getARoom(roomNumberToReserve);
            var reservation = ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
            System.out.println("Reservation created -- \n" + reservation.toString());
        }
        else if (userResponse.equalsIgnoreCase("N") || userResponse.equalsIgnoreCase("No")) {
            createAccount(scanner);
            System.out.println("Returning to the main menu.");
            printMainMenu();
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