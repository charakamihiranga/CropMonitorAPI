package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.CropDTO;

public interface CropService {
    void saveCrop(@Valid CropDTO cropDTO);
}
