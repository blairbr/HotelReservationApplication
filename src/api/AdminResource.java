package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //provide a static reference

    public Customer getCustomer(String email) {
        return null;
        //to get to compile
    };

    public void addRoom(List<IRoom> rooms) {  //should AddRoom be taking in a List of rooms? Dont we just want to add one room here?
        //to get to compile
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
