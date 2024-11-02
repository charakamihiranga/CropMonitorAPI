package lk.ijse.springboot.CropMonitorAPI.exception;

public class DataPersistFailedException extends RuntimeException {
    public DataPersistFailedException() {
        super("Data persisting failed");
    }

    public DataPersistFailedException(String message) {
        super(message);
    }

    public DataPersistFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
