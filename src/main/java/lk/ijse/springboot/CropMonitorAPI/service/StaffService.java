package lk.ijse.springboot.CropMonitorAPI.service;

import lk.ijse.springboot.CropMonitorAPI.dto.StaffDTO;
import lk.ijse.springboot.CropMonitorAPI.entity.Staff;
import lk.ijse.springboot.CropMonitorAPI.response.StaffResponse;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO) throws Exception;
    void deleteStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staff);
    StaffResponse getSelectedStaff(String staffId);
    List<StaffDTO> getAllStaff();
}
