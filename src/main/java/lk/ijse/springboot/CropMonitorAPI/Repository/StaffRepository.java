package lk.ijse.springboot.CropMonitorAPI.Repository;

import lk.ijse.springboot.CropMonitorAPI.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, String> {
}
