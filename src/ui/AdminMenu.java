package ui;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;
import java.util.*;

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
                    System.out.println("5. Seed Test Data");
                    System.out.println("6. Back to Main Menu");

                    String adminMenuChoice = scanner.nextLine();
                    int adminMenuChoiceAsInteger = Integer.parseInt(adminMenuChoice);

                    switch (adminMenuChoiceAsInteger) {
                        case 1:
                            printAllCustomers();
                            MainMenu.printMainMenu();
                            continueRunning = false;
                            break;
                        case 2:
                            continueRunning = false;
                            displayAllRooms();
                            MainMenu.printMainMenu();
                            break;
                        case 3:
                            AdminResource.getInstance().displayAllReservations();
                            continueRunning = false;
                            MainMenu.printMainMenu();
                            break;
                        case 4:
                            continueRunning = false;
                            addARoom(scanner);
                            MainMenu.printMainMenu();
                            break;
                        case 5:
                            continueRunning = false;
                            seedTestData();
                            MainMenu.printMainMenu();
                            break;
                        case 6:
                            System.out.println("Returning to the main menu.");
                            continueRunning = false;
                            MainMenu.printMainMenu();
                            break;
                        default:
                            break;

                    }
                }
                catch (Exception ex) {
                    System.out.println("Error - Invalid Input. Returning to Main Menu.");
                }
            }
        }
    }

    private static void printAllCustomers() {
        var allCustomers = AdminResource.getInstance().getAllCustomers();

        if (allCustomers.isEmpty()) {
            System.out.println("No customers have been found in the system.");
            return;
        }
        for (Customer customer: allCustomers) {
            System.out.println(customer);
        }
    }

    private static void seedTestData() {
        IRoom room101 = new Room(150.0, RoomType.SINGLE, "101");
        IRoom room102 = new Room(175.0, RoomType.DOUBLE, "102");
        IRoom room103 = new Room(200.0, RoomType.DOUBLE, "103");
        IRoom room104 = new Room(200.0, RoomType.SINGLE, "104");
        List<IRoom> testRooms = Arrays.asList(room101, room102, room103, room104);
        AdminResource.getInstance().addRooms(testRooms);

        Customer customer1 = new Customer("billjenkins@hotmail.com", "Bill", "Jenkins");
        Customer customer2 = new Customer("Danielle.Riveras@gmail.com", "Danielle", "Riveras-Rojas");
        Customer customer3 = new Customer("Arjun.Submaranian@juno.com", "Arjun", "Submaranian");
        Customer customer4 = new Customer("me@me.com", "Blair", "Brown");

        List<Customer> customersList = new ArrayList<Customer>(Arrays.asList(customer1, customer2, customer3, customer4));

        for (Customer customer : customersList) {
            CustomerService.getInstance().addCustomer(customer.getEmail(), customer.getFirstName(), customer.getLastName());
        }

        Calendar calendar = Calendar.getInstance();

        calendar.set(2022, Calendar.JULY, 2);
        Date july2 = calendar.getTime();

        calendar.set(2022, Calendar.JULY, 7);
        Date july7 = calendar.getTime();

        calendar.set(2022, Calendar.APRIL, 1);
        Date april1 = calendar.getTime();

        calendar.set(2022, Calendar.APRIL, 4);
        Date april4 = calendar.getTime();

        calendar.set(2022, Calendar.AUGUST, 27);
        Date august27 = calendar.getTime();

        calendar.set(2022, Calendar.AUGUST, 28);
        Date august28 = calendar.getTime();

        calendar.set(2022, Calendar.SEPTEMBER, 17);
        Date sept17 = calendar.getTime();

        calendar.set(2022, Calendar.SEPTEMBER, 19);
        Date sept19 = calendar.getTime();

        var reservation1 = HotelResource.getInstance().bookARoom(customer1.getEmail(), room101, april1, april4);
        var reservation2 = HotelResource.getInstance().bookARoom(customer2.getEmail(), room102, july2, july7);
        var reservation3 = HotelResource.getInstance().bookARoom(customer3.getEmail(), room102, august27, august28);
        var reservation4 = HotelResource.getInstance().bookARoom(customer4.getEmail(), room104, august27, august28);
        var reservation5 = HotelResource.getInstance().bookARoom(customer4.getEmail(), room104, sept17, sept19);
    }

    private static void displayAllRooms() {
        var allRooms = AdminResource.getInstance().getAllRooms();

        if (allRooms.isEmpty()) {
            System.out.println("No rooms have been found in the system.");
            return;
        }

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
            roomNumberIsValid = checkIfRoomNumberIsValid(roomNumberAsString);
        }

        System.out.println("Enter price per night: ");
        String pricePerNightAsString = scanner.nextLine();
        double pricePerNight = Double.parseDouble(pricePerNightAsString);

        System.out.println("Enter room type - 1 for single bed or 2 for double bed: ");
        String roomTypeAsString = scanner.nextLine().toUpperCase();

        RoomType roomType = RoomType.SINGLE;

        if (roomTypeAsString.equals("1")) {
            roomType = RoomType.SINGLE;
        }

        else if (roomTypeAsString.equals("2")) {
            roomType = RoomType.DOUBLE;
        }

        IRoom room = new Room(pricePerNight, roomType, roomNumberAsString);
        List<IRoom> rooms = new ArrayList<>();

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

    private static boolean checkIfRoomNumberIsValid(String roomNumberAsString) {
        try {
            Integer.parseInt(roomNumberAsString);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number.");
            return false;
        }
    }
}
