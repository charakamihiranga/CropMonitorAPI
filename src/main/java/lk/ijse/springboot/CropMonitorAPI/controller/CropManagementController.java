package lk.ijse.springboot.CropMonitorAPI.controller;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.CropDTO;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.service.CropService;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crop")
@RequiredArgsConstructor
public class CropManagementController {
    private final CropService cropService;
    static Logger logger = LoggerFactory.getLogger(CropManagementController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestParam("commonName")String commonName,
            @RequestParam("scientificName")String scientificName,
            @RequestParam("category")String category,
            @RequestParam("season")String season,
            @RequestParam("fieldCode")String fieldCode,
            @RequestParam("monitoringLogCodes") List<String> monitoringLogCodes,
            @RequestParam("cropImage") MultipartFile cropImage
    ) {
        if (commonName == null || scientificName == null || category == null || season == null || fieldCode == null
                || monitoringLogCodes == null || cropImage == null) {
            logger.warn("Invalid request: Crop object or Crop Image is null");
            return ResponseEntity.badRequest().build();
        } else {
            CropDTO cropDTO = new CropDTO();
            try {
                cropDTO.setCropCommonName(commonName);
                cropDTO.setCropScientificName(scientificName);
                cropDTO.setCategory(category);
                cropDTO.setCropSeason(season);
                cropDTO.setFieldCode(fieldCode);
                cropDTO.setMonitoringLogCodes(monitoringLogCodes);
                cropDTO.setCropImage(AppUtil.toBase64Pic(cropImage));
                cropService.saveCrop(cropDTO);
                logger.info("Crop with Crop Code: {} saved successfully", cropDTO.getCropCode());
                return ResponseEntity.ok().build();
            } catch (DataPersistFailedException e) {
                logger.warn("Failed to save crop: {}", cropDTO, e);
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error("Internal server error while saving crop: {}", cropDTO, e);
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
