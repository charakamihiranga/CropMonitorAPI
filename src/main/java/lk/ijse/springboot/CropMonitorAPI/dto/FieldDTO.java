package lk.ijse.springboot.CropMonitorAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO{
    private String fieldCode;
    @NotBlank
    @Size(max = 50)
    private String fieldName;
    @NotNull
    private Point fieldLocation;
    @Positive
    private double fieldSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<String> cropCodes;
    private List<String> equipmentIds;
    private List<String> staffIds;
    private List<String> monitoringLogCodes;
}
