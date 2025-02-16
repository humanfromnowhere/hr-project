package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.RolDTO;
import com.ikenvanter.envanter.entity.Rol;
import com.ikenvanter.envanter.repo.RolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private RolRepo rolRepo;

    public List<RolDTO> getAllRoles() {
        List<Rol> roles = rolRepo.findAll();
        return roles.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RolDTO getRoleById(Long id) {
        Optional<Rol> rol = rolRepo.findById(id);
        return rol.map(this::convertToDto).orElse(null);
    }

    public RolDTO saveRole(RolDTO rolDTO) {
        Rol rol = convertToEntity(rolDTO);
        rol = rolRepo.save(rol);
        return convertToDto(rol);
    }

    public RolDTO updateRole(Long id, RolDTO rolDTO) {
        Optional<Rol> rolOptional = rolRepo.findById(id);
        if (rolOptional.isPresent()) {
            Rol rol = convertToEntity(rolDTO);
            rol.setId(id);
            rol = rolRepo.save(rol);
            return convertToDto(rol);
        }
        return null;
    }

    public void deleteRole(Long id) {
        rolRepo.deleteById(id);
    }

    private RolDTO convertToDto(Rol rol) {
        return new RolDTO(rol.getId(), rol.getName());
    }

    private Rol convertToEntity(RolDTO rolDTO) {
        return new Rol(rolDTO.getId(), rolDTO.getName(), null);
    }
}
