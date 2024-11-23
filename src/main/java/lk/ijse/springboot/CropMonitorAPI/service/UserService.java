package lk.ijse.springboot.CropMonitorAPI.service;

import jakarta.validation.Valid;
import lk.ijse.springboot.CropMonitorAPI.dto.UserDTO;
import lk.ijse.springboot.CropMonitorAPI.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void addUser(@Valid UserDTO userDTO);
    void removeUser(String email);
    void updateUser(String email, UserDTO userDTO);
    UserResponse getUser(String email);
    List<UserDTO> getAllUsers();

    UserDetailsService userDetailsService();
}
