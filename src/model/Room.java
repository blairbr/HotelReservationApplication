package model;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    @Override
    public boolean isFree() {
        return false;
    }

    public Room(Double price, RoomType roomType, String roomNumber) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public String toString() {
        return "Room #" + roomNumber +  " - Price: $" + price + " - Room Type: " + roomType;
    }
}
