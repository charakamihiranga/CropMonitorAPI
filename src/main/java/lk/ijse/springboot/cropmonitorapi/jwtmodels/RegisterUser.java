package lk.ijse.springboot.cropmonitorapi.jwtmodels;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterUser {
    private String email;
    private String password;
}
