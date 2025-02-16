package com.ikenvanter.envanter.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "roller")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol_adi", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roller", fetch = FetchType.EAGER)
    private Set<Kullanici> kullanicilar;

    public Rol() {
    }

    public Rol(Long id, String name, Set<Kullanici> kullanicilar) {
        this.id = id;
        this.name = name;
        this.kullanicilar = kullanicilar;
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

    public Set<Kullanici> getKullanicilar() {
        return kullanicilar;
    }

    public void setKullanıcılar(Set<Kullanici> kullanicilar) {
        this.kullanicilar = kullanicilar;
    }
}
