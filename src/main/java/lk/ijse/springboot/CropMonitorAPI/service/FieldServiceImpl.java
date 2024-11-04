package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.Repository.FieldRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.FieldDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Field;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.exception.FieldNotFoundException;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lk.ijse.springboot.CropMonitorAPI.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
