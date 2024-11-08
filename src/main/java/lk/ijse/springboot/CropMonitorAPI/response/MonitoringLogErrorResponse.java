package lk.ijse.springboot.CropMonitorAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogErrorResponse implements MonitoringLogResponse{
    private int errorCode;
    private String errorMessage;
}
