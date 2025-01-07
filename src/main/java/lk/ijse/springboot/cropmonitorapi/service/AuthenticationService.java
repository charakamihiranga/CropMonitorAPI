package lk.ijse.springboot.cropmonitorapi.service;

import lk.ijse.springboot.cropmonitorapi.exception.InvalidCredentialsException;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.AuthResponse;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.RegisterUser;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.UserLogin;

import java.nio.file.AccessDeniedException;

public interface AuthenticationService {
    AuthResponse registerUser(RegisterUser user) throws AccessDeniedException;
    AuthResponse login(UserLogin login) throws InvalidCredentialsException;
    AuthResponse refreshToken(String refreshToken);
}
