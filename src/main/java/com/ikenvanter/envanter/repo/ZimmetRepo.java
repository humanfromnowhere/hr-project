package com.ikenvanter.envanter.repo;

import com.ikenvanter.envanter.entity.Zimmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZimmetRepo extends JpaRepository<Zimmet, Long> {
    List<Zimmet> findByPersonelId(Long personelId);
    List<Zimmet> findByEnvanterId(Long envanterId);
}
