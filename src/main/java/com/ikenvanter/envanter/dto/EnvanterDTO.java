package com.ikenvanter.envanter.dto;

import java.time.LocalDate;
import java.util.List;
import com.ikenvanter.envanter.entity.Zimmet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnvanterDTO {
    private Long id;
    private String tipi;
    private String marka;
    private String model;
    private String seriNumarasi;
    private LocalDate girisTarihi;
    private String statu;
    private int toplamSayi;
    private int kullanimdaOlanSayi;
    private int kullanimdaOlmayanSayi;
    private List<Zimmet> zimmetler;
  
}
