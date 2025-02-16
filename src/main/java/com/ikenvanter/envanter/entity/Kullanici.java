package com.ikenvanter.envanter.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Optional;
import java.util.Set;

import com.ikenvanter.envanter.dto.KullaniciDTO;

@Data
@Entity
@Table(name = "kullanici")
public class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kullanici_adi", nullable = false, unique = true)
    private String username;

    @Column(name = "parola", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "kullanici_roller", 
      joinColumns = @JoinColumn(name = "kullanici_id"), 
      inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roller;

    public Kullanici() {
    }

    public Kullanici(Long id, String username, String password, Set<Rol> roller) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roller = roller;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<Rol> getRoller() {
        return roller;
    }
    
    public void setRoller(Set<Rol> roller) {
        this.roller = roller;
    }

    public Optional<KullaniciDTO> map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
