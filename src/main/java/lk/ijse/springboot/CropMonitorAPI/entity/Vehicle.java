package lk.ijse.springboot.CropMonitorAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class Vehicle implements SuperEntity{
    @Id
    private String vehicleCode;
    private String licensePlateNumber;
    private String  vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    //  staff entity =>  allocated staff member
}
