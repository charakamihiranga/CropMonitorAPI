package lk.ijse.springboot.cropmonitorapi.service.impl;

import lk.ijse.springboot.cropmonitorapi.entity.Role;
import lk.ijse.springboot.cropmonitorapi.entity.User;
import lk.ijse.springboot.cropmonitorapi.exception.InvalidCredentialsException;
import lk.ijse.springboot.cropmonitorapi.exception.UserAlreadyExistsException;
import lk.ijse.springboot.cropmonitorapi.exception.UserNotFoundException;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.AuthResponse;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.RegisterUser;
import lk.ijse.springboot.cropmonitorapi.jwtmodels.UserLogin;
import lk.ijse.springboot.cropmonitorapi.repository.StaffRepository;
import lk.ijse.springboot.cropmonitorapi.repository.UserRepository;
import lk.ijse.springboot.cropmonitorapi.service.AuthenticationService;
import lk.ijse.springboot.cropmonitorapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registerUser(RegisterUser user) throws AccessDeniedException {
        if (!userRepository.existsUserByEmail(user.getEmail())) {
            Optional<Role> optionalRole = staffRepository.findRoleIfEmailExists(user.getEmail());
            if (optionalRole.isPresent()) {
                if (optionalRole.get() != Role.OTHER) {
                    User register = new User(
                            user.getEmail(),
                            user.getPassword(),
                            optionalRole.get()
                    );
                    User saved = userRepository.save(register);
                    String generatedToken = jwtService.generateToken(saved);
                    String userFullName = staffRepository.findFullNameByEmail(user.getEmail());
                    return AuthResponse.builder()
                            .token(generatedToken)
                            .userFullName(userFullName)
                            .role(saved.getRole())
                            .build();
                } else {
                    throw new AccessDeniedException("You are not allowed to register as a user");
                }
            } else {
                throw new UserNotFoundException("Cannot find manager assigned with this email");
            }
        }
        throw new UserAlreadyExistsException("User already exists");
    }

    @Override
    public AuthResponse login(UserLogin login) throws InvalidCredentialsException {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            var userByEmail = userRepository.findByEmail(login.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            String generatedToken = jwtService.generateToken(userByEmail);
            String userFullName = staffRepository.findFullNameByEmail(login.getEmail());
            return AuthResponse.builder()
                    .token(generatedToken)
                    .userFullName(userFullName)
                    .role(userByEmail.getRole())
                    .build();
        } catch (UserNotFoundException | AuthenticationException e){
            throw new InvalidCredentialsException("Invalid credential");
        }
    }



    @Override
    public AuthResponse refreshToken(String accessToken) {
        var username = jwtService.extractUserEmail(accessToken);
        var userEntity =
                userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return AuthResponse.builder().token(refreshToken).build();
    }
}
