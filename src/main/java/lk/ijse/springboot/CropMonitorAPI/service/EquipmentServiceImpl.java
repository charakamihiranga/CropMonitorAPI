package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.Repository.EquipmentRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.EquipmentDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Equipment;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.exception.EquipmentNotFoundException;
import lk.ijse.springboot.CropMonitorAPI.response.EquipmentErrorResponse;
import lk.ijse.springboot.CropMonitorAPI.response.EquipmentResponse;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lk.ijse.springboot.CropMonitorAPI.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService{
    private final Mapping mapping;
    private final EquipmentRepository equipmentRepository;

    @Override
    public void saveEquipment(EquipmentDTO equipment) {
        equipment.setEquipmentId(AppUtil.generateId("EQ"));
        Equipment saved = equipmentRepository.save(mapping.map(equipment, Equipment.class));
        if (saved.getEquipmentId() == null){
            throw new DataPersistFailedException("Failed to save equipment data!");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<Equipment> optEquipmentById = equipmentRepository.findById(equipmentId);
        if (optEquipmentById.isEmpty()){
            throw new EquipmentNotFoundException("Equipment not found");
        } else {
            equipmentRepository.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipment) {
        equipmentRepository.findById(equipmentId).ifPresentOrElse(
                selectedEquipment -> {
                    equipment.setEquipmentId(selectedEquipment.getEquipmentId());
                    equipmentRepository.save(mapping.map(equipment, Equipment.class));
                }, () -> {
                    throw new EquipmentNotFoundException("Equipment not found");
                }
        );
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {
        if (equipmentRepository.existsById(equipmentId)){
            return mapping.map(equipmentRepository.getById(equipmentId), EquipmentDTO.class);
        } else {
            return new EquipmentErrorResponse(404, "Equipment not found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return mapping.mapList(equipmentRepository.findAll(), EquipmentDTO.class);
    }
}
