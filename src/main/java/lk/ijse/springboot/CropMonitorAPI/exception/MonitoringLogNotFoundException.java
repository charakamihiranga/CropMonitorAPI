package lk.ijse.springboot.CropMonitorAPI.exception;

public class MonitoringLogNotFoundException extends RuntimeException {
    public MonitoringLogNotFoundException() {
        super();
    }

    public MonitoringLogNotFoundException(String message) {
        super(message);
    }

    public MonitoringLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
