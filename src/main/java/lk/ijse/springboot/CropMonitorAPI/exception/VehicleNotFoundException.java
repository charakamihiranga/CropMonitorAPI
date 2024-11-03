package lk.ijse.springboot.CropMonitorAPI.exception;

import lk.ijse.springboot.CropMonitorAPI.entity.Vehicle;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException() {
        super();
    }

    public VehicleNotFoundException(String message) {
        super(message);
    }

    public VehicleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
