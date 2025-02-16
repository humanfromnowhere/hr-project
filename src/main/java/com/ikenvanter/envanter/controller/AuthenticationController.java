package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.AuthenticationRequest;
import com.ikenvanter.envanter.dto.AuthenticationResponse;
import com.ikenvanter.envanter.jwt.JwtUtil;
import com.ikenvanter.envanter.security.KullaniciDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KullaniciDetayService kullaniciDetayService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı adı ve ya şifre hatalı");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }

        final UserDetails userDetails = kullaniciDetayService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        System.out.println("Oluşturulan JWT: " + jwt); //token oluştuktan sonra loglar

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
