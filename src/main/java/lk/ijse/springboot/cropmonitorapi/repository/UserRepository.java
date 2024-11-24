package lk.ijse.springboot.cropmonitorapi.repository;

import lk.ijse.springboot.cropmonitorapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);
}
