package lk.ijse.springboot.CropMonitorAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements SuperDTO{
    private String logCode;
    private Date logDate;
    private String observation;
    private String observedImage;
    private List<String> fieldCodes;
    private List<String> cropCodes;
    private List<String> staffIds;
}
