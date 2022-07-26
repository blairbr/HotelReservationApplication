package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    //no 1. private constructors
    private ReservationService() {};
    //no 2.
    private static ReservationService reservationService = new ReservationService();
    //no 3.
    public static ReservationService getInstance() {
        return reservationService;
    }

    //stuffs
    //Create a Collections to store and retrieve a reservation
    private Collection<Reservation> reservations = new ArrayList<>();

    //A collection to store all the rooms
    private Map<String, IRoom> roomMap = new HashMap<>();

    public void addRoom(IRoom room) {
        try {
            checkIfRoomAlreadyExists(room);
        }
        catch (Exception ex) {
            ex.getLocalizedMessage();
        }
        roomMap.put(room.getRoomNumber(), room);
    }

    private void checkIfRoomAlreadyExists(IRoom room) throws DuplicateRoomException {
        //try()
        if (roomMap.containsKey(room.getRoomNumber())) {
            //throw an exception and catch it higher up the chain.
            throw new DuplicateRoomException("This room number already exists in the system.");
        }
    }

    public IRoom getARoom(String roomId) {
            return null;
            //to get to compile
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        return null;
        //to get to compile
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;
        //to get to compile
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return null;
        //to get to compile
    }
    public void printAllReservation() {
    }
}
