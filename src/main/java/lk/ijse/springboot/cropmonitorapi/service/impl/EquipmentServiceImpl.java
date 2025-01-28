package lk.ijse.springboot.cropmonitorapi.service.impl;


import lk.ijse.springboot.cropmonitorapi.entity.Field;
import lk.ijse.springboot.cropmonitorapi.entity.Staff;
import lk.ijse.springboot.cropmonitorapi.exception.FieldNotFoundException;
import lk.ijse.springboot.cropmonitorapi.exception.StaffNotFoundException;
import lk.ijse.springboot.cropmonitorapi.repository.EquipmentRepository;
import lk.ijse.springboot.cropmonitorapi.dto.EquipmentDTO;
import lk.ijse.springboot.cropmonitorapi.entity.Equipment;
import lk.ijse.springboot.cropmonitorapi.exception.DataPersistFailedException;
import lk.ijse.springboot.cropmonitorapi.exception.EquipmentNotFoundException;
import lk.ijse.springboot.cropmonitorapi.repository.FieldRepository;
import lk.ijse.springboot.cropmonitorapi.repository.StaffRepository;
import lk.ijse.springboot.cropmonitorapi.response.impl.EquipmentErrorResponse;
import lk.ijse.springboot.cropmonitorapi.response.EquipmentResponse;
import lk.ijse.springboot.cropmonitorapi.service.EquipmentService;
import lk.ijse.springboot.cropmonitorapi.util.AppUtil;
import lk.ijse.springboot.cropmonitorapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final Mapping mapping;
    private final EquipmentRepository equipmentRepository;
    private final StaffRepository staffRepository;
    private final FieldRepository fieldRepository;

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
            throw new EquipmentNotFoundException("Equipment not found ");
        } else {
            equipmentRepository.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDto) {
        Equipment selectedEquipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with ID: " + equipmentId));

        equipmentDto.setEquipmentId(selectedEquipment.getEquipmentId());
        Equipment equipment = mapping.map(equipmentDto, Equipment.class);

        Staff staff = staffRepository.findById(equipmentDto.getStaffId())
                .orElseThrow(() -> new StaffNotFoundException("Staff not found with ID: " + equipmentDto.getStaffId()));
        equipment.setStaff(staff);

        Field field = fieldRepository.findById(equipmentDto.getFieldCode())
                .orElseThrow(() -> new FieldNotFoundException("Field not found with code: " + equipmentDto.getFieldCode()));
        equipment.setField(field);

        equipmentRepository.save(equipment);
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
