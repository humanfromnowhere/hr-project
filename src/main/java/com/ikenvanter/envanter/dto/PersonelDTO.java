package com.ikenvanter.envanter.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data

public class PersonelDTO {
    private Long id;
    private String ad;
    private String soyadi;
    private String cinsiyet;
    private LocalDate dogumTarihi;
    private String medeniDurum;
    private String tckn;
    private String sicilNo;
    private String mezuniyet;
    private String birim;
    private String pozisyon;
    private String calismaDurumu;
    private LocalDate giris;
    private byte[] profilFoto;
    private LocalDate cikis;
    private String neden;
    private String unvan;
    private List<ZimmetDTO> zimmetler;
   
}
