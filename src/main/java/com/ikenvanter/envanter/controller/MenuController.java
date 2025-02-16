package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.MenuDTO;
import com.ikenvanter.envanter.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        MenuDTO menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping("/createm")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) {
        MenuDTO savedMenu = menuService.saveMenu(menuDTO);
        return ResponseEntity.status(201).body(savedMenu);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<MenuDTO>> getMenusByRole(@PathVariable String role) {
        List<MenuDTO> menus = menuService.getMenusByRole(role);
        return ResponseEntity.ok(menus);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
