package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private ReservationService() {}
    private static final ReservationService reservationService = new ReservationService();
    public static ReservationService getInstance() {
        return reservationService;
    }

    private final List<Reservation> reservations = new ArrayList<>();

    private Map<String, IRoom> roomMap = new HashMap<>();

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
        System.out.println("Room getRoomNumber() " + room.getRoomNumber());
        if (roomMap.containsKey(room.getRoomNumber())) {
            throw new DuplicateRoomException("This room number already exists in the system.");
        }
    }

    public IRoom getARoom(String roomId) {
       return roomMap.get(roomId);
    }

    public Collection<IRoom> getAllRooms() {
        return roomMap.values();
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
        for(IRoom room : allRooms){
            if(roomIsNotBooked(room)){
                availableRooms.add(room);
            }
        }

        for(Reservation reservation : reservations){
            boolean requestedCheckInAndCheckOutDatesAreBothBEFOREReservationCheckIn = false;
            boolean requestedCheckInAndCheckOutDatesAreBothAFTERReservationCheckIn = false;
            boolean availableRoomsListAlreadyContainsTheRoom = false;

            Date reservationCheckInDate = reservation.getCheckInDate();
            Date reservationCheckOutDate = reservation.getCheckOutDate();

            if (checkInDate.before(reservationCheckInDate) && checkOutDate.before(reservationCheckInDate))
            {
                requestedCheckInAndCheckOutDatesAreBothBEFOREReservationCheckIn = true;
            }
            if (checkInDate.after(reservationCheckOutDate) && checkOutDate.after(reservationCheckOutDate)) {
                requestedCheckInAndCheckOutDatesAreBothAFTERReservationCheckIn = true;
            }
            if (availableRooms.contains(reservation.getRoom())) {
                availableRoomsListAlreadyContainsTheRoom = true;
            }

            if (!availableRoomsListAlreadyContainsTheRoom &&  (requestedCheckInAndCheckOutDatesAreBothBEFOREReservationCheckIn || requestedCheckInAndCheckOutDatesAreBothAFTERReservationCheckIn))  {
                availableRooms.add(reservation.getRoom());
            }
        }

        return availableRooms;
    }

    //default access modifier
    boolean roomIsNotBooked(IRoom room){
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
