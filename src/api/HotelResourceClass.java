package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResourceClass {


    //Provide a static reference
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
