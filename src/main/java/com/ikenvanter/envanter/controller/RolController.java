package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.RolDTO;
import com.ikenvanter.envanter.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listr")
    public ResponseEntity<List<RolDTO>> getAllRoles() {
        List<RolDTO> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRoleById(@PathVariable Long id) {
        RolDTO rolDTO = rolService.getRoleById(id);
        if (rolDTO != null) {
            return ResponseEntity.ok(rolDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/creater")
    public ResponseEntity<RolDTO> createRole(@RequestBody RolDTO rolDTO) {
        RolDTO savedRole = rolService.saveRole(rolDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> updateRole(@PathVariable Long id, @RequestBody RolDTO rolDTO) {
        RolDTO updatedRole = rolService.updateRole(id, rolDTO);
        if (updatedRole != null) {
            return ResponseEntity.ok(updatedRole);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        rolService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
