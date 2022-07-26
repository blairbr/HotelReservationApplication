package service;

public class DuplicateRoomException extends Exception {
    public DuplicateRoomException(String errorMessage) {
        super(errorMessage);
    }
}
