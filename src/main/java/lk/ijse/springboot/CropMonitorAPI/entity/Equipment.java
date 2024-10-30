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
@Table(name = "equipment")
public class Equipment implements SuperEntity{
    @Id
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private Status status;
    // staff entity =>  assigned staff details // can be null
    // field entity =>  assigned field details // can be null
}
