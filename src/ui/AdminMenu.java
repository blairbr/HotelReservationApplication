package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
                            //See all Rooms
                            continueRunning = false;
                            displayAllRooms();
                            //sleep for 2 seconds so the menu doesn't cover the rooms
                            TimeUnit.SECONDS.sleep(2);
                            MainMenu.printMainMenu();
                            break;
                        case 3:
                            continueRunning = false;
                            break;
                        case 4:
                            continueRunning = false;
                            //add a room
                            addARoom(scanner);
                            break;
                        case 5:
                            System.out.println("Returning to the main menu.");
                            continueRunning = false;
                            MainMenu.printMainMenu();
                            break;
                        default:
                            break;

                    }
                }
                catch (Exception ex) {
                    System.out.println("Error - Invalid Input. Enter a value between 1 and 5.");
                }
            }
        }
    }

    private static void displayAllRooms() {
        var allRooms = AdminResource.getInstance().getAllRooms();

        for (IRoom room:allRooms) {
            System.out.println(room);
        }

    }

    private static void addARoom(Scanner scanner) {
        String roomNumberAsString = "";
        boolean roomNumberIsValid = false;

        while (!roomNumberIsValid) {
            System.out.println("Enter a room number");
            roomNumberAsString = scanner.nextLine();
            roomNumberIsValid = checkIfRoomNumberIsValid(roomNumberAsString, scanner);
        }

        System.out.println("Enter price per night: ");
        String pricePerNightAsString = scanner.nextLine();
        double pricePerNight = Double.parseDouble(pricePerNightAsString);

        System.out.println("Enter room type - 1 for single bed or 2 for double bed: ");
        String roomTypeAsString = scanner.nextLine().toUpperCase();

        //default to single - fix this later and add validation - there has to be a way to use the value of too
        RoomType roomType = RoomType.SINGLE;

        if (roomTypeAsString.equals("1")) {
            roomType = RoomType.SINGLE;
        }

        else if (roomTypeAsString.equals("2")) {
            roomType = RoomType.DOUBLE;
        }

        IRoom room = new Room(pricePerNight, roomType, roomNumberAsString);
        List<IRoom> rooms = new ArrayList<IRoom>();

        rooms.add(room);
        AdminResource.getInstance().addRooms(rooms);

        System.out.println("Would you like to add another room? Y or N");
        String userChoiceToAddAnotherRoom = scanner.nextLine();

        if (userChoiceToAddAnotherRoom.equalsIgnoreCase("Y")) {
            addARoom(scanner);
        }

        else System.out.println("Returning to the Main Menu.");
        MainMenu.printMainMenu();
    }

    private static boolean checkIfRoomNumberIsValid(String roomNumberAsString, Scanner scanner) {
        try {
            Integer.parseInt(roomNumberAsString);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number.");
            return false;
        }
    }
}
