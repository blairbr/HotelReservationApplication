package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import ui.MainMenu;

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
        if (customer == null) {
            System.out.println("Customer was not found. Returning to main menu");
            MainMenu.printMainMenu();
        }
        return customer;
    }
    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService.getInstance().addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
       return ReservationService.getInstance().getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        var customer = HotelResource.getInstance().getCustomer(customerEmail);
        return ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        return ReservationService.getInstance().getCustomersReservations(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.getInstance().findRooms(checkIn, checkOut);
    }
}
