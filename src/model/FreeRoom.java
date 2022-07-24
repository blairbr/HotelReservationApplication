package model;

public class FreeRoom extends Room {
    public FreeRoom(Double price, RoomType roomType, String roomNumber) {
        super(0.0, roomType, roomNumber);
    }

    @Override
    public String toString() {
        return super.toString() + "Yay, you got a free room!!!";
    }
}
