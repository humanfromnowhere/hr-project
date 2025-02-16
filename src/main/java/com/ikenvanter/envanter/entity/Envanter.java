package com.ikenvanter.envanter.entity;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "envanter")
public class Envanter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(name="Envanter tipi",nullable = false,length = 50)
    @NotBlank(message = "Envanter tipi boş olamaz")
    private String tipi;

    @Column(name="Marka",nullable = false,length = 50)
    @NotBlank(message = "Marka boş bırakılamaz")
    private String marka;
    
    @Column(name="Model",nullable = false,length = 50)
    @NotBlank(message = "Model boş bırakılamaz")
    private String model;
    
    @Column(name = "Seri_no",nullable = false, unique = true)
    @NotNull(message = "Seri numarası boş bırakılamaz")
    @Pattern(regexp = "\\d+", message = "Seri numarası sadece sayılardan oluşmalıdır")
    private String seriNumarasi;

    @Column(name = "Giris_tarihi", nullable = false)
    @NotNull(message = "Giriş tarihi boş bırakılamaz")
    private LocalDate girisTarihi;

    @Column(name = "statu", nullable = false, length = 50)
    @NotBlank(message = "Statü boş bırakılamaz")
    private String statu;
    
    @Column(name = "toplam_sayi", nullable = false)
    private int toplamSayi;

    @Column(name = "kullanimda_olan_sayi", nullable = false)
    private int kullanimdaOlanSayi;
    
    @Column(name = "kullanimda_olmayan_sayi", nullable = false)
    private int kullanimdaOlmayanSayi;

    @OneToMany(mappedBy = "envanter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Zimmet> zimmetler;
    
    public Envanter (){}
    
    public Envanter(Long id, @NotBlank(message = "Envanter tipi boş olamaz") String tipi,
            @NotBlank(message = "Marka boş bırakılamaz") String marka,
            @NotBlank(message = "Model boş bırakılamaz") String model,
            @NotNull(message = "Seri numarası boş bırakılamaz") @Pattern(regexp = "\\d+", message = "Seri numarası sadece sayılardan oluşmalıdır") String seriNumarasi,
            @NotNull(message = "Giriş tarihi boş bırakılamaz") LocalDate girisTarihi,
            @NotBlank(message = "Statü boş bırakılamaz") String statu, int toplamSayi, int kullanimdaOlanSayi,
            int kullanimdaOlmayanSayi, List<Zimmet> zimmetler) {
        this.id = id;
        this.tipi = tipi;
        this.marka = marka;
        this.model = model;
        this.seriNumarasi = seriNumarasi;
        this.girisTarihi = girisTarihi;
        this.statu = statu;
        this.toplamSayi = toplamSayi;
        this.kullanimdaOlanSayi = kullanimdaOlanSayi;
        this.kullanimdaOlmayanSayi = kullanimdaOlmayanSayi;
        this.zimmetler = zimmetler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeriNumarasi() {
        return seriNumarasi;
    }

    public void setSeriNumarasi(String seriNumarasi) {
        this.seriNumarasi = seriNumarasi;
    }

    public LocalDate getGirisTarihi() {
        return girisTarihi;
    }

    public void setGirisTarihi(LocalDate girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public int getToplamSayi() {
        return toplamSayi;
    }

    public void setToplamSayi(int toplamSayi) {
        this.toplamSayi = toplamSayi;
    }

    public int getKullanimdaOlanSayi() {
        return kullanimdaOlanSayi;
    }

    public void setKullanimdaOlanSayi(int kullanimdaOlanSayi) {
        this.kullanimdaOlanSayi = kullanimdaOlanSayi;
    }

    public int getKullanimdaOlmayanSayi() {
        return kullanimdaOlmayanSayi;
    }

    public void setKullanimdaOlmayanSayi(int kullanimdaOlmayanSayi) {
        this.kullanimdaOlmayanSayi = kullanimdaOlmayanSayi;
    }

    public List<Zimmet> getZimmetler() {
        return zimmetler;
    }

    public void setZimmetler(List<Zimmet> zimmetler) {
        this.zimmetler = zimmetler;
    }
}
