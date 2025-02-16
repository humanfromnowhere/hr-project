package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.KullaniciDTO;
import com.ikenvanter.envanter.dto.RolDTO;
import com.ikenvanter.envanter.entity.Kullanici;
import com.ikenvanter.envanter.entity.Rol;
import com.ikenvanter.envanter.repo.KullaniciRepo;
import com.ikenvanter.envanter.repo.RolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KullaniciService {

    @Autowired
    private KullaniciRepo kullaniciRepo;

    @Autowired
    private RolRepo rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public KullaniciDTO save(KullaniciDTO kullaniciDTO) {
        Kullanici kullanici = new Kullanici();
        kullanici.setUsername(kullaniciDTO.getUsername());
        kullanici.setPassword(passwordEncoder.encode(kullaniciDTO.getPassword()));
        
        
        Set<Rol> roller = new HashSet<>();
        for (RolDTO rolDTO : kullaniciDTO.getRoller()) {
            Optional<Rol> rolOpt = rolRepo.findByName(rolDTO.getName());
            Rol rol;
            if (rolOpt.isPresent()) {
                rol = rolOpt.get();
            } else {
                rol = new Rol();
                rol.setName(rolDTO.getName());
                rol = rolRepo.save(rol); 
            }
            roller.add(rol);
        }
        kullanici.setRoller(roller);

        Kullanici savedKullanici = kullaniciRepo.save(kullanici);

        kullaniciDTO.setId(savedKullanici.getId());
        kullaniciDTO.setPassword(null); 
        return kullaniciDTO;
    }

    public Optional<KullaniciDTO> findByUsername(String username) {
        return kullaniciRepo.findByUsername(username).map(kullanici -> {
            KullaniciDTO dto = new KullaniciDTO();
            dto.setId(kullanici.getId());
            dto.setUsername(kullanici.getUsername());
            dto.setRoller(kullanici.getRoller().stream().map(rol -> {
                RolDTO rolDTO = new RolDTO();
                rolDTO.setId(rol.getId());
                rolDTO.setName(rol.getName());
                return rolDTO;
            }).collect(Collectors.toSet()));
            return dto;
        });
    }

    public boolean checkPassword(KullaniciDTO kullaniciDTO, String rawPassword) {
        return kullaniciRepo.findByUsername(kullaniciDTO.getUsername())
                .map(kullanici -> passwordEncoder.matches(rawPassword, kullanici.getPassword()))
                .orElse(false);
    }
}
