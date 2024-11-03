package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.VehicleDTO;
import lk.ijse.springboot.CropMonitorAPI.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    void updateVehicle(String vehicleCode, VehicleDTO vehicle);
    VehicleResponse getSelectedVehicle(String vehicleCode);
    List<VehicleDTO> getAllVehicles();
}
