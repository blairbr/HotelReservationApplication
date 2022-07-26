package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //0. provide a static reference

    //1. create an object of Admin Resource
    private static AdminResource adminResource = new AdminResource();

    //2. make the constructor private so that this class cannot be instantiated
    private AdminResource() {}

    //3. get the instance (the only one available)
    public static AdminResource getInstance() {
        return adminResource;
    }

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
