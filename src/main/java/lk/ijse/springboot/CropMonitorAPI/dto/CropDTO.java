package lk.ijse.springboot.CropMonitorAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO {
    private String cropCode;
    @NotBlank
    @Size(max = 50)
    private String cropCommonName;
    @NotBlank
    @Size(max = 50)
    private String cropScientificName;
    @NotBlank
    private String category;
    @NotBlank
    private String cropSeason;
    //must be not null
    private String fieldCode;
    private List<String> monitoringLogCodes;
    private String cropImage;
}
