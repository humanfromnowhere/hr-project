package com.ikenvanter.envanter.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "zimmet")
public class Zimmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personel_id", nullable = false)
    private Personel personel;

    @ManyToOne
    @JoinColumn(name = "envanter_id", nullable = false)
    private Envanter envanter;

    @Column(name = "envanterin_verilme_tarihi", nullable = false)
    private LocalDate verilmeTarihi;

    @Column(name = "geri_alinma_tarihi")
    private LocalDate geriAlinmaTarihi;

    @Column(name = "teslim_eden_adi_soyadi", nullable = false)
    @NotBlank(message = "Teslim eden kişinin adı ve soyadı boş bırakılamaz")
    private String teslimEden;

    @Column(name = "teslim_alan_adi_soyadi", nullable = false)
    @NotBlank(message = "Teslim alanın adı ve soyadı boş bırakılamaz")
    private String teslimAlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    public void setEnvanter(Envanter envanter) {
        this.envanter = envanter;
    }

    public LocalDate getVerilmeTarihi() {
        return verilmeTarihi;
    }

    public void setVerilmeTarihi(LocalDate verilmeTarihi) {
        this.verilmeTarihi = verilmeTarihi;
    }

    public LocalDate getGeriAlinmaTarihi() {
        return geriAlinmaTarihi;
    }

    public void setGeriAlinmaTarihi(LocalDate geriAlinmaTarihi) {
        this.geriAlinmaTarihi = geriAlinmaTarihi;
    }

    public String getTeslimEden() {
        return teslimEden;
    }

    public void setTeslimEden(String teslimEden) {
        this.teslimEden = teslimEden;
    }

    public String getTeslimAlan() {
        return teslimAlan;
    }

    public void setTeslimAlan(String teslimAlan) {
        this.teslimAlan = teslimAlan;
    }

    

}
