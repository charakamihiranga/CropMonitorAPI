package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.CropDTO;
import lk.ijse.springboot.CropMonitorAPI.response.CropResponse;

import java.util.List;


public interface CropService {
    void saveCrop(@Valid CropDTO cropDTO);
    void deleteCrop(String cropCode);
    void updateCrop(String cropCode, CropDTO cropDTO);
    CropResponse getSelectedCrop(String cropCode);
    List<CropDTO> getAllCrops();
}
