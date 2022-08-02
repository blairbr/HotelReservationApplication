package service;

public class InvalidCheckInDatesException extends Exception {
    public InvalidCheckInDatesException(String errorMessage) {
        super(errorMessage);
    }
}