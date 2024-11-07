package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.CropMonitorAPI.Repository.FieldRepository;
import lk.ijse.springboot.CropMonitorAPI.Repository.StaffRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.FieldDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Field;
import lk.ijse.springboot.CropMonitorAPI.entity.Staff;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.exception.FieldNotFoundException;
import lk.ijse.springboot.CropMonitorAPI.response.FieldErrorResponse;
import lk.ijse.springboot.CropMonitorAPI.response.FieldResponse;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lk.ijse.springboot.CropMonitorAPI.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService{
    private final FieldRepository fieldRepository;
    private final Mapping mapping;
    private final StaffRepository staffRepository;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.generateId("FE"));
        Field field = mapping.map(fieldDTO, Field.class);
        if (fieldDTO.getStaffIds() != null && !fieldDTO.getStaffIds().isEmpty()){
            List<Staff> staffList = staffRepository.findAllById(fieldDTO.getStaffIds());
            field.setStaff(staffList);
        }
        Field saved = fieldRepository.save(field);
        if (saved.getFieldCode() == null){
            throw new DataPersistFailedException("Failed to save field data!");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        fieldRepository.findById(fieldCode).ifPresentOrElse(
                field -> fieldRepository.deleteById(fieldCode),
                () -> {
                    throw new FieldNotFoundException("Field not found!");
                }
        );
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Field existingField = fieldRepository.findById(fieldCode)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        Field map = mapping.map(fieldDTO, Field.class);
        map.setFieldCode(existingField.getFieldCode());
        if (fieldDTO.getStaffIds() != null && !fieldDTO.getStaffIds().isEmpty()){
            List<Staff> staffList = staffRepository.findAllById(fieldDTO.getStaffIds());
            map.setStaff(staffList);
        }
        fieldRepository.save(map);
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        if (fieldRepository.existsById(fieldCode)){
            return mapping.map(fieldRepository.getById(fieldCode), FieldDTO.class);
        } else {
            return new FieldErrorResponse(404, "Field not found");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.mapList(fieldRepository.findAll(), FieldDTO.class);
    }

}
