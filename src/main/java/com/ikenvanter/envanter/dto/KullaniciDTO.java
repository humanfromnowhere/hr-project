package com.ikenvanter.envanter.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class KullaniciDTO {
    private Long id;
    private String username;
    private String password;
    private Set<RolDTO> roller = new HashSet<>(); 

   
}
