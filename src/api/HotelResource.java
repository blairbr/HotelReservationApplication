package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

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
        var customer = CustomerService.getInstance().getCustomer(email);
        return customer;
    }
    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService.getInstance().addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        //this should basically just call the service class
        return null;
        //to get to compile
        //
        // why are these methods public and not private??
    }

    //need to get the customer by the email
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        var customer = HotelResource.getInstance().getCustomer(customerEmail);
        var reservation = ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
        return reservation;
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
