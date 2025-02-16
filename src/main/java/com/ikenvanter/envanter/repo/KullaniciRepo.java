package com.ikenvanter.envanter.repo;

import com.ikenvanter.envanter.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KullaniciRepo extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByUsername(String username);

   
}
