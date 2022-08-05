package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static final AdminResource adminResource = new AdminResource();

    private AdminResource() {}

    public static AdminResource getInstance() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return HotelResource.getInstance().getCustomer(email);
    };

    public void addRooms(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.getInstance().addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        var allRooms = ReservationService.getInstance().getAllRooms();
        return allRooms;
    }

    public List<Customer> getAllCustomers() {
        var customers = CustomerService.getInstance().getAllCustomers();
        return customers;
    };

    public void displayAllReservations() {
        ReservationService.getInstance().printAllReservations();
    };
}
