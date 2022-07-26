package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {


    //0. Provide a static reference

    //1. create/new-up an object of Admin Resource
    private static HotelResource hotelResource = new HotelResource();

    //2. make the constructor private so that this class cannot be instantiated
    private HotelResource() {
    }
    //3. get the instance of the class
    public static HotelResource getInstance() {
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        //this should basically just call the service class
        return null;
        //to get to compile
    }
    public void createACustomer(String email, String firstName, String lastName) {
    }

    public IRoom getRoom(String roomNumber) {
        //this should basically just call the service class
        return null;
        //to get to compile
        //
        // why are these methods public and not private??
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;
        //to get to compile
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        return null;
        //to get to compile
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return null;
        //to get to compile
    }
}
