package com.ikenvanter.envanter.repo;

import com.ikenvanter.envanter.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    List<Menu> findByRole(String role);
}
