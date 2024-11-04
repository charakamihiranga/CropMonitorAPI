package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.FieldDTO;

public interface FieldService {
    void saveField(@Valid FieldDTO field);
    void deleteField(String fieldCode);
}
