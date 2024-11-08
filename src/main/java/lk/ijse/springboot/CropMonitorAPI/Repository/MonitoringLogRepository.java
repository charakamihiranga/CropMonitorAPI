package lk.ijse.springboot.CropMonitorAPI.Repository;

import lk.ijse.springboot.CropMonitorAPI.entity.MonitoringLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLogRepository extends JpaRepository<MonitoringLog, String> {
}
