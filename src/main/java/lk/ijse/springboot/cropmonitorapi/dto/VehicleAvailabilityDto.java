package lk.ijse.springboot.cropmonitorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleAvailabilityDto implements SuperDTO {
    private long availableVehicleCount;
    private long unavailableVehicleCount;
}
