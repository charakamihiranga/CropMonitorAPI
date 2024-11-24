package lk.ijse.springboot.cropmonitorapi.repository;

import lk.ijse.springboot.cropmonitorapi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
