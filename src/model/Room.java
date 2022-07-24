package model;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(Double price, RoomType roomType, String roomNumber) {
        super();  //do i need this?
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "RoomNumber: " + roomNumber +  "Price: " + price + " Room Type: " + roomType;
    }
}
