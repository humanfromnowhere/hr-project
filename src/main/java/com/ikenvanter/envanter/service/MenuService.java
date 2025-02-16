package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.MenuDTO;
import com.ikenvanter.envanter.entity.Menu;
import com.ikenvanter.envanter.repo.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    public MenuDTO saveMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setName(menuDTO.getName());
        menu.setUrl(menuDTO.getUrl());
        menu.setRole(menuDTO.getRole());
        
        Menu savedMenu = menuRepo.save(menu);

        menuDTO.setId(savedMenu.getId());
        return menuDTO;
    }

    public MenuDTO getMenuById(Long id) {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new RuntimeException("Menu bulunamadı"));
        return convertToDTO(menu);
    }

    public List<MenuDTO> getMenusByRole(String role) {
        List<Menu> menus = menuRepo.findByRole(role);
        return menus.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteMenu(Long id) {
        menuRepo.deleteById(id);
    }

    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setName(menu.getName());
        menuDTO.setUrl(menu.getUrl());
        menuDTO.setRole(menu.getRole());
        return menuDTO;
    }
}
