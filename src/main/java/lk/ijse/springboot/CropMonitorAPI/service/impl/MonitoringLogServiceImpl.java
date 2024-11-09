package lk.ijse.springboot.CropMonitorAPI.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springboot.CropMonitorAPI.Repository.CropRepository;
import lk.ijse.springboot.CropMonitorAPI.Repository.FieldRepository;
import lk.ijse.springboot.CropMonitorAPI.Repository.MonitoringLogRepository;
import lk.ijse.springboot.CropMonitorAPI.Repository.StaffRepository;
import lk.ijse.springboot.CropMonitorAPI.dto.MonitoringLogDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Crop;
import lk.ijse.springboot.CropMonitorAPI.entity.Field;
import lk.ijse.springboot.CropMonitorAPI.entity.MonitoringLog;
import lk.ijse.springboot.CropMonitorAPI.entity.Staff;
import lk.ijse.springboot.CropMonitorAPI.exception.DataPersistFailedException;
import lk.ijse.springboot.CropMonitorAPI.exception.MonitoringLogNotFoundException;
import lk.ijse.springboot.CropMonitorAPI.response.MonitoringLogResponse;
import lk.ijse.springboot.CropMonitorAPI.service.MonitoringLogService;
import lk.ijse.springboot.CropMonitorAPI.util.AppUtil;
import lk.ijse.springboot.CropMonitorAPI.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MonitoringLogServiceImpl implements MonitoringLogService {
    private final MonitoringLogRepository mlRepository;
    private final Mapping mapping;
    private final FieldRepository fieldRepository;
    private final CropRepository cropRepository;
    private final StaffRepository staffRepository;
    private final MonitoringLogRepository monitoringLogRepository;

    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
       monitoringLogDTO.setLogCode(AppUtil.generateId("ML"));
       monitoringLogDTO.setLogDate(AppUtil.getCurrentDateTime());
       MonitoringLog monitoringLog = mapping.map(monitoringLogDTO, MonitoringLog.class);
       // setFields to monitoringLog
        List<Field> fieldList = !monitoringLogDTO.getFieldCodes().isEmpty()
            ? fieldRepository.findAllById(monitoringLogDTO.getFieldCodes())
            : Collections.emptyList();
        monitoringLog.setFields(fieldList);
        // setCrops to monitoringLog
        List<Crop> cropList = !monitoringLogDTO.getCropCodes().isEmpty()
            ? cropRepository.findAllById(monitoringLogDTO.getCropCodes())
            : Collections.emptyList();
        monitoringLog.setCrops(cropList);
        // setStaff to monitoringLog
        List<Staff> staffList = !monitoringLogDTO.getStaffIds().isEmpty()
            ? staffRepository.findAllById(monitoringLogDTO.getStaffIds())
            : Collections.emptyList();
        monitoringLog.setStaff(staffList);
        MonitoringLog saved = mlRepository.save(monitoringLog);
        if (saved.getLogCode() == null){
            throw new DataPersistFailedException("Failed to save monitoring log data!");
        }
    }

    @Override
    public void deleteMonitoringLog(String logCode) {
        mlRepository.findById(logCode).ifPresentOrElse(
            monitoringLog -> mlRepository.deleteById(logCode),
            () -> {
                throw new MonitoringLogNotFoundException("Monitoring Log not found!");
            }
        );
    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO) {
        MonitoringLog existingMonitoringLog = mlRepository.findById(logCode)
            .orElseThrow(() -> new MonitoringLogNotFoundException("Monitoring Log not found!"));
        MonitoringLog map = mapping.map(monitoringLogDTO, MonitoringLog.class);
        map.setLogCode(existingMonitoringLog.getLogCode());
        // setFields to monitoringLog
        List<Field> fieldList = !monitoringLogDTO.getFieldCodes().isEmpty()
            ? fieldRepository.findAllById(monitoringLogDTO.getFieldCodes())
            : Collections.emptyList();
        map.setFields(fieldList);
        // setCrops to monitoringLog
        List<Crop> cropList = !monitoringLogDTO.getCropCodes().isEmpty()
            ? cropRepository.findAllById(monitoringLogDTO.getCropCodes())
            : Collections.emptyList();
        map.setCrops(cropList);
        // setStaff to monitoringLog
        List<Staff> staffList = !monitoringLogDTO.getStaffIds().isEmpty()
            ? staffRepository.findAllById(monitoringLogDTO.getStaffIds())
            : Collections.emptyList();
        map.setStaff(staffList);
        mlRepository.save(map);
    }

    @Override
    public MonitoringLogResponse getSelectedMonitoringLog(String logCode) {
        return monitoringLogRepository.findById(logCode)
                .map(log -> {
                    MonitoringLogDTO dto = mapping.map(log, MonitoringLogDTO.class);

                    dto.setFieldCodes(log.getFields().stream()
                            .map(Field::getFieldCode)
                            .collect(Collectors.toList()));

                    dto.setCropCodes(log.getCrops().stream()
                            .map(Crop::getCropCode)
                            .collect(Collectors.toList()));

                    dto.setStaffIds(log.getStaff().stream()
                            .map(Staff::getStaffId)
                            .collect(Collectors.toList()));

                    return dto;
                })
                .orElseThrow(() -> new MonitoringLogNotFoundException("Monitoring Log not found"));
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        return mapping.mapList(mlRepository.findAll(), MonitoringLogDTO.class);
    }

}