package com.ikenvanter.envanter.repo;

import com.ikenvanter.envanter.entity.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol, Long> {
   Optional< Rol> findByName(String name);
}
