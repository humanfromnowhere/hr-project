package com.ikenvanter.envanter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikenvanter.envanter.entity.Personel;

import java.util.List;

@Repository
public interface PersonelRepo extends JpaRepository<Personel, Long> {

    @Query("SELECT p FROM Personel p WHERE p.sicilNo LIKE %:sicilNo% OR p.ad LIKE %:ad%")
    List<Personel> findBySicilNoContainingOrAdiContaining(@Param("sicilNo") String sicilNo, @Param("ad") String ad);

    @Query("SELECT p FROM Personel p WHERE p.ad LIKE %:adi%")
    List<Personel> findByAdiContaining(@Param("adi") String adi);

    @Query("SELECT p FROM Personel p WHERE p.soyadi LIKE %:soyadi%")
    List<Personel> findBySoyadiContaining(@Param("soyadi") String soyadi);

    @Query("SELECT p FROM Personel p WHERE p.tckn = :tckn")
    List<Personel> findByTckn(@Param("tckn") String tckn);

    @Query("SELECT p FROM Personel p WHERE p.birim = :birim")
    List<Personel> findByBirim(@Param("birim") String birim);
    
    @Query("SELECT p FROM Personel p WHERE p.sicilNo = :sicilNo")
    List<Personel> findBySicilNo(@Param("sicilNo") String sicilNo);
}