package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    //no 1. private constructors
    private ReservationService() {};  //isnt this just the default constructor?
    //no 2.
    private static ReservationService reservationService = new ReservationService();
    //no 3.
    public static ReservationService getInstance() {
        return reservationService;
    }

    //stuffs
    //Create a Collections to store and retrieve a reservatin
    private Collection<Reservation> reservations = new ArrayList<>();

    private Map<String, IRoom> roomMap = new HashMap<>();

    public void addRoom(IRoom room) {

    };
    public IRoom getARoom(String roomId) {
            return null;
            //to get to compile
    };
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        return null;
        //to get to compile
    };
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;
        //to get to compile
    };
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return null;
        //to get to compile
    };
    public void printAllReservation() {
    }
}
