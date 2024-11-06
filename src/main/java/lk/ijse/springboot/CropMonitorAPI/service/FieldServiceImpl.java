package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.Repository.FieldRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.FieldDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Field;
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
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService{
    private final FieldRepository fieldRepository;
    private final Mapping mapping;

    @Override
    public void saveField(FieldDTO field) {
        field.setFieldCode(AppUtil.generateId("FE"));
        Field saved = fieldRepository.save(mapping.map(field, Field.class));
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
        fieldRepository.findById(fieldCode).ifPresentOrElse(
                selectedField -> {
                    fieldDTO.setFieldCode(selectedField.getFieldCode());
                    fieldRepository.save(mapping.map(fieldDTO, Field.class));
                }, () -> {
                    throw new FieldNotFoundException("Field not found");
                }
        );
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
