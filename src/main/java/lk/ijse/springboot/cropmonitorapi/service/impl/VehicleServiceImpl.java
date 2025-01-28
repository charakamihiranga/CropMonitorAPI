package lk.ijse.springboot.cropmonitorapi.service.impl;

import lk.ijse.springboot.cropmonitorapi.dto.VehicleAvailabilityDto;
import lk.ijse.springboot.cropmonitorapi.entity.Staff;
import lk.ijse.springboot.cropmonitorapi.exception.StaffNotFoundException;
import lk.ijse.springboot.cropmonitorapi.repository.StaffRepository;
import lk.ijse.springboot.cropmonitorapi.repository.VehicleRepository;
import lk.ijse.springboot.cropmonitorapi.dto.VehicleDTO;
import lk.ijse.springboot.cropmonitorapi.entity.Vehicle;
import lk.ijse.springboot.cropmonitorapi.exception.DataPersistFailedException;
import lk.ijse.springboot.cropmonitorapi.exception.VehicleNotFoundException;
import lk.ijse.springboot.cropmonitorapi.response.impl.VehicleErrorResponse;
import lk.ijse.springboot.cropmonitorapi.response.VehicleResponse;
import lk.ijse.springboot.cropmonitorapi.service.VehicleService;
import lk.ijse.springboot.cropmonitorapi.util.AppUtil;
import lk.ijse.springboot.cropmonitorapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final Mapping mapping;
    private final VehicleRepository vehicleRepository;
    private final StaffRepository staffRepository;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.generateId("VEH"));
        Vehicle saved = vehicleRepository.save(mapping.map(vehicleDTO, Vehicle.class));
        if (saved.getVehicleCode() == null){
            throw new DataPersistFailedException("Failed to save vehicle data!");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode){
        Optional<Vehicle> vehicleOptById = vehicleRepository.findById(vehicleCode);
        if (vehicleOptById.isEmpty()){
            throw new VehicleNotFoundException("Vehicle not found");
        } else {
            vehicleRepository.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDto) {
        Vehicle selectedVehicle = vehicleRepository.findById(vehicleCode)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with code: " + vehicleCode));

        Vehicle mappedVehicle = mapping.map(vehicleDto, Vehicle.class);
        mappedVehicle.setVehicleCode(selectedVehicle.getVehicleCode());

        Staff staff = staffRepository.findById(vehicleDto.getStaffId())
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with ID: " + vehicleDto.getStaffId()));
        mappedVehicle.setStaff(staff);

        vehicleRepository.save(mappedVehicle);
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        if (vehicleRepository.existsById(vehicleCode)){
            return mapping.map(vehicleRepository.getById(vehicleCode), VehicleDTO.class);
        } else {
            return new VehicleErrorResponse(404, "Vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.mapList(vehicleRepository.findAll(), VehicleDTO.class);
    }

    @Override
    public VehicleAvailabilityDto getVehicleAvailabilityCount() {
        long availableCount = vehicleRepository.countByStatus("available");
        long unAvailableCount = vehicleRepository.countByStatus("unavailable");
        return new VehicleAvailabilityDto(availableCount, unAvailableCount);
    }

}
