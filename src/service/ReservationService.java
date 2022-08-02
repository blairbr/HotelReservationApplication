package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    //no 1. private constructors
    private ReservationService() {}
    //no 2.
    private static final ReservationService reservationService = new ReservationService();
    //no 3.
    public static ReservationService getInstance() {
        return reservationService;
    }

    //stuffs
    //Create a list to store and retrieve a reservation
    private final List<Reservation> reservations = new ArrayList<>();

    //A hashmap (dictionary) to store all the rooms by key roomnumber
    private Map<String, IRoom> roomMap = new HashMap<>();

    //hashmap (aka dictionary) of rooms and reservations, lookup by room number?
    Map<IRoom, List<Reservation>> mapOfReservations = new HashMap<>();

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
        var listOfRooms = roomMap.values();
        return listOfRooms;
        //return this directly after testing/debugging;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        var reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        try {
            if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate))
            {
                throw new InvalidCheckInDatesException("Check out date must be after the check in date.");
            }
            reservations.add(reservation);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return reservation;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> availableRooms = new ArrayList<>();

        var allRooms = getAllRooms();
        // find all the rooms that aren't booked at all
        for(IRoom room : allRooms){

            if(findUnbookedRoom(room)){
                availableRooms.add(room);
            }

        }
        for(Reservation reservation : reservations){
            Date reservationCheckInDate = reservation.getCheckInDate();
            Date reservationCheckOutDate = reservation.getCheckOutDate();
            if(checkInDate.before(reservationCheckInDate) && checkOutDate.before(reservationCheckInDate) || checkInDate.after(reservationCheckOutDate) && checkOutDate.after(reservationCheckOutDate)
                && !availableRooms.contains(reservation.getRoom())) {
                availableRooms.add(reservation.getRoom());
            }
        }

        return availableRooms;
    }

    boolean findUnbookedRoom(IRoom room){
        boolean isUnbooked = true;
        for(Reservation r: reservations){
            if(room.getRoomNumber().equals(r.getRoom().getRoomNumber())){
                isUnbooked = false;
            }
        }
        return isUnbooked;
    }

    public Collection<Reservation> getCustomersReservations(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (Objects.equals(customer.getEmail(), reservation.getCustomer().getEmail())) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
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
