package com.example.diplom33.controllers;


import com.example.diplom33.dto.JwtRequest;
import com.example.diplom33.dto.JwtResponse;
import com.example.diplom33.dto.RegistrationUserDTO;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.Code;
import com.example.diplom33.security.JwtUtil;
import com.example.diplom33.services.AuthService;
import com.example.diplom33.services.CodeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;
    private final CodeService codeService;

//    private final UserService userService;

    private final AuthService authService;

    private final JwtUtil jwtUtil;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {

            throw new NoSuchException("Неправильный логин или пароль");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegistrationUserDTO registrationUser) {

        authService.createUser(registrationUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        return new ResponseEntity<>("пользователь создан",headers, HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return new ResponseEntity<>("пользователь вышел", HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<String> createCode(@RequestParam String typeUser) {
        Code code = codeService.createCode(typeUser);
        return ResponseEntity.ok(code.getCode());
    }
}
