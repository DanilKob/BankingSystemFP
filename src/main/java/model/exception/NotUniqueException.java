package model.exception;

public class NotUniqueException extends Exception{
    private String notUniqueValue;

    public NotUniqueException(String notUniqueValue) {
        this.notUniqueValue = notUniqueValue;
    }

    public String getNotUniqueValue() {
        return notUniqueValue;
    }

    public void setNotUniqueValue(String notUniqueValue) {
        this.notUniqueValue = notUniqueValue;
    }
}
