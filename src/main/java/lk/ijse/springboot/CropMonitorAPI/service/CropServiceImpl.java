package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.Repository.CropRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.CropDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Crop;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lk.ijse.springboot.CropMonitorAPI.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {
    private final CropRepository cropRepository;
    private final Mapping mapping;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.generateId("CR"));
        Crop saved = cropRepository.save(mapping.map(cropDTO, Crop.class));
        if (saved.getCropCode() == null){
            throw new DataPersistFailedException("Failed to save crop data");
        }
    }
}
