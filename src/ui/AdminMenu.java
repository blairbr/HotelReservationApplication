package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void printAdminMenu() {

        boolean continueRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (continueRunning) {
                try {
                    System.out.println("ADMIN MENU - Please choose from one of the options below.");

                    System.out.println("1. See all Customers");
                    System.out.println("2. See all Rooms");
                    System.out.println("3. See all Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Back to Main Menu");

                    String adminMenuChoice = scanner.nextLine();
                    int adminMenuChoiceAsInteger = Integer.parseInt(adminMenuChoice);

                    switch (adminMenuChoiceAsInteger) {
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
                            continueRunning = false;
                            //add a room
                            addARoom(scanner);
                           // adminResource.addRoom();
                            break;
                        case 5:
                            System.out.println("Returning to the main menu.");
                            continueRunning = false;
                            MainMenu.printMainMenu();
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

    private static void addARoom(Scanner scanner) {
        System.out.println("Enter a room number");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night: ");
        String pricePerNightAsString = scanner.nextLine();
        double pricePerNight = Double.parseDouble(pricePerNightAsString);

        System.out.println("Enter room type: 1 for Single bed or 2 for double bed.: ");
        String roomTypeAsString = scanner.nextLine();
        RoomType roomType = RoomType.valueOf(roomTypeAsString);

        Room room = new Room(pricePerNight, roomType, roomNumber);
        AdminResource.getInstance().addRoom((List<IRoom>) room);  //find a better way to do this
    }
}
