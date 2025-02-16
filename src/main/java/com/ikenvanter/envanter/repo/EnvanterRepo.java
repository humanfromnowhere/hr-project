package com.ikenvanter.envanter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ikenvanter.envanter.entity.Envanter;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnvanterRepo extends JpaRepository<Envanter, Long> {
    @Query("SELECT e FROM Envanter e WHERE e.tipi = :tipi")
    List<Envanter> findByTipi(@Param("tipi") String tipi);

    @Query("SELECT e FROM Envanter e WHERE e.marka LIKE %:marka%")
    List<Envanter> findByMarkaContaining(@Param("marka") String marka);

    @Query("SELECT e FROM Envanter e WHERE e.girisTarihi BETWEEN :startDate AND :endDate")
    List<Envanter> findByGirisTarihiBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT e FROM Envanter e WHERE e.kullanimdaOlanSayi > 0")
    List<Envanter> findKullanimdaOlanlar();

    @Query("SELECT e FROM Envanter e WHERE e.kullanimdaOlmayanSayi > 0")
    List<Envanter> findKullanimdaOlmayanlar();

    @Query("SELECT e FROM Envanter e WHERE e.tipi LIKE %:tipi% OR e.marka LIKE %:marka%")
    List<Envanter> findByTipiContainingOrMarkaContaining(@Param("tipi") String tipi, @Param("marka") String marka);
}
