package lk.ijse.springboot.cropmonitorapi.repository;

import lk.ijse.springboot.cropmonitorapi.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
}
