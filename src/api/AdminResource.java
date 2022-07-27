package api;

import model.Customer;
import model.IRoom;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //0. provide a static reference

    //1. create an object of Admin Resource
    private static final AdminResource adminResource = new AdminResource();

    //2. make the constructor private so that this class cannot be instantiated
    private AdminResource() {}

    //3. get the instance (the only one available) return the singleton
    public static AdminResource getInstance() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return null;
        //to get to compile
    };

    public void addRooms(List<IRoom> rooms) {  //should AddRoom be taking in a List of rooms? Dont we just want to add one room here?
        for (IRoom room : rooms) {
            ReservationService.getInstance().addRoom(room);
        }
    };

    public Collection<IRoom> getAllRooms() {
        return null;
        //to get to compile
    };

    public Collection<Customer> getAllCustomers() {
        return null;
        //to get to compile
    };

    public void displayAllReservations() {

    };
}
