package lk.ijse.springboot.CropMonitorAPI.Repository;

import lk.ijse.springboot.CropMonitorAPI.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
}
