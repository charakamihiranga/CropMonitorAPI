package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.FieldDTO;
import lk.ijse.springboot.CropMonitorAPI.response.FieldResponse;

import java.util.List;

public interface FieldService {
    void saveField(@Valid FieldDTO field);
    void deleteField(String fieldCode);
    void updateField(String fieldCode, FieldDTO fieldDTO);
    FieldResponse getSelectedField(String fieldCode);
    List<FieldDTO> getAllFields();
}
