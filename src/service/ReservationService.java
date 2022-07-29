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
    private Map<String, IRoom> roomMap = new HashMap<String, IRoom>();

    public void addRoom(IRoom room) {
        try {
            checkIfRoomAlreadyExists(room);
            roomMap.put(room.getRoomNumber(), room);
            System.out.println("ROOM LIST = " + roomMap);
            if (roomMap.containsKey(room.getRoomNumber())) {
                System.out.println("Room " + room.getRoomNumber() + " was successfully added.");
            }
        }
        catch (Exception ex) {
            //ex.getLocalizedMessage();
            System.out.println(ex.getLocalizedMessage());
        }

    }

    private void checkIfRoomAlreadyExists(IRoom room) throws DuplicateRoomException {
        //try()
        System.out.println("PRINTING THE MAP AT THIS POINT: " +  roomMap);

        System.out.println("Room getRoomNumber() " + room.getRoomNumber());
        if (roomMap.containsKey(room.getRoomNumber())) {
            //throw an exception and catch it higher up the chain.
            throw new DuplicateRoomException("This room number already exists in the system.");
        }
    }

    public IRoom getARoom(String roomId) {
            return null;
            //to get to compile
    }

    public Collection<IRoom> getAllRooms() {
        System.out.println("Called getAllRooms() in the ReservationService");
        return roomMap.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        var reservation = new Reservation(customer, room, checkInDate, checkoutDate);
        try {
            reservations.add(reservation);
        }
        catch (Exception ex) {

        }
        return reservation;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;
        //to get to compile

    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return null;
        //to get to compile
    }
    public void printAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations were found in the system.");
            return;
        }
        for (Reservation reservation : reservations ) {
            System.out.println(reservation.toString());
        }

    }
}
