package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.KullaniciDTO;
import com.ikenvanter.envanter.service.KullaniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/kullanici")
public class KullaniciController {

    @Autowired
    private KullaniciService kullaniciService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<KullaniciDTO> registerKullanici(@RequestBody KullaniciDTO kullaniciDTO) {
        KullaniciDTO savedKullanici = kullaniciService.save(kullaniciDTO);
        return new ResponseEntity<>(savedKullanici, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginKullanici(@RequestBody KullaniciDTO loginRequest) {
        System.out.println("buraya düştü");
        if (kullaniciService.checkPassword(loginRequest, loginRequest.getPassword())) {
            return new ResponseEntity<>("Login başarılı", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Geçersiz kullanıcı adı veya şifre", HttpStatus.UNAUTHORIZED);
        }
    }
}
