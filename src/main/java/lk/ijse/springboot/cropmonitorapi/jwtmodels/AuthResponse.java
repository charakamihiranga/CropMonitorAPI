package lk.ijse.springboot.cropmonitorapi.jwtmodels;

import lk.ijse.springboot.cropmonitorapi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthResponse {
    private String token;
    private String userFullName;
    private Role role;
}
