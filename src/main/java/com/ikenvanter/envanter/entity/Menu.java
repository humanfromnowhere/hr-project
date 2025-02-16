package com.ikenvanter.envanter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;

@Entity
@Table(name = "menü")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="Menünün adı",nullable=false)
    @NotBlank(message = "Menü adı boş olamaz")
    private String name;
    
    @Column(name="URL",nullable = false)
    @NotBlank(message = "URL boş olamaz")
    private String url;
    
    @Column(name="Rol")
    @NotBlank(message = "Rol boş olamaz")
    private String role;
    
    public Menu(){}
    public Menu(Long id, @NotBlank(message = "Menü adı boş olamaz") String name,
            @NotBlank(message = "URL boş olamaz") String url, @NotBlank(message = "Rol boş olamaz") String role) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
