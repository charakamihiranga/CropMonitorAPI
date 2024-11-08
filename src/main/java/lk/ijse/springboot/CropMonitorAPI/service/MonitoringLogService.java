package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.dto.MonitoringLogDTO;
import lk.ijse.springboot.CropMonitorAPI.response.MonitoringLogResponse;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO);
    void deleteMonitoringLog(String logCode);
    void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO);
}
