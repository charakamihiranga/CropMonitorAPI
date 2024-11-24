package lk.ijse.springboot.cropmonitorapi.service;

import lk.ijse.springboot.cropmonitorapi.exception.InvalidCredentialsException;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.AuthResponse;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.RegisterUser;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.UserLogin;

public interface AuthenticationService {
    AuthResponse registerUser(RegisterUser user);
    AuthResponse login(UserLogin login) throws InvalidCredentialsException;
    AuthResponse refreshToken(String refreshToken);
}
