package lk.ijse.springboot.CropMonitorAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop_detail")
public class CropDetail implements SuperEntity{
    @Id
    private String logCode;
    private Date logDate;
    private String observation;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    // crop field list =>  crop
    // staff field list =>  staff
    // field list =>  field
}
