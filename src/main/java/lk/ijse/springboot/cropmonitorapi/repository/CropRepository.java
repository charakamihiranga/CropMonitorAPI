package lk.ijse.springboot.cropmonitorapi.repository;

import lk.ijse.springboot.cropmonitorapi.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}
