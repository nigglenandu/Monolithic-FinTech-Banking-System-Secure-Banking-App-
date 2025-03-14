package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.AuthController;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.Role;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.RoleEntity;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model.UserEntity;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.payload.JwtResponse;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.payload.LoginRequest;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.payload.SignupRequest;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.repository.RoleRepository;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.repository.UserRepository;
import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateTokenFromUserName(userDetails.getUsername());

        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userEntityOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: User not found!");
        }

        UserEntity user = userEntityOptional.get();

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getRole().name())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, roles,
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if(userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());

        Optional<RoleEntity> userRole = roleRepository.findByRole(Role.ROLE_USER);
        if(userRole.isPresent()) {
            user.setRoles(Collections.singleton(userRole.get()));
        } else {
            return ResponseEntity.badRequest().body("Error: Default role not found!");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("User logged out successfully!");
    }
}
