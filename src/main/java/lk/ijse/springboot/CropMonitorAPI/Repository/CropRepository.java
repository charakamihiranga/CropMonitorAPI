package lk.ijse.springboot.CropMonitorAPI.Repository;

import lk.ijse.springboot.CropMonitorAPI.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}
