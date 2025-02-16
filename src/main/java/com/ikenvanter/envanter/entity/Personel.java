package com.ikenvanter.envanter.entity;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "personel")
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad", nullable = false, length = 50)
    @NotBlank(message = "Ad boş bırakılamaz")
    private String ad;

    @Column(name = "soyadi", nullable = false, length = 60)
    @NotBlank(message = "Soyadı boş bırakılamaz")
    private String soyadi;

    @Column(name = "cinsiyet", nullable = false, length = 1)
    @Pattern(regexp = "M|F", message = "Cinsiyet sadece M veya F harflerinden oluşabilir")
    @NotBlank(message = "Cinsiyet boş bırakılamaz")
    private String cinsiyet;

    @Column(name = "dogum_tarihi", nullable = false)
    @NotNull(message = "Doğum tarihi boş olamaz")
    private LocalDate dogumTarihi;

    @Column(name = "medeni_durum", nullable = false)
    @NotBlank(message = "Medeni durum boş bırakılamaz")
    @Pattern(regexp = "Evli|Bekar", message = "Medeni durum sadece 'Evli' veya 'Bekar' olabilir")
    private String medeniDurum;

    @Column(name = "tckn", nullable = false, length = 11, unique = true)
    @NotBlank(message = "TC no boş bırakılamaz")
    @Pattern(regexp = "\\d{11}", message = "TCKN 11 haneli sayı olmalıdır")
    private String tckn;

    @Column(name = "sicil_no", nullable = false, unique = true)
    @NotNull(message = "Sicil numarası boş olamaz")
    @Pattern(regexp = "\\d+", message = "Sicil numarası sadece sayılardan oluşmalıdır")
    private String sicilNo;

    @Column(name = "mezuniyet")
    private String mezuniyet;

    @Column(name = "birim", nullable = false, length = 50)
    @NotBlank(message = "Birim boş bırakılamaz")
    private String birim;

    @Column(name = "pozisyon", nullable = false, length = 50)
    @NotBlank(message = "Pozisyon boş bırakılamaz")
    private String pozisyon;

    @Column(name = "calisma_durumu", nullable = false, length = 50)
    @NotBlank(message = "Çalışma durumu boş bırakılamaz")
    @Pattern(regexp = "Evet|Hayır", message = "Çalışma durumu sadece 'Evet' veya 'Hayır' olabilir")
    private String calismaDurumu;

    @Column(name = "ise_giris_tarihi", nullable = false)
    @NotNull(message = "İşe giriş tarihi boş olamaz")
    private LocalDate giris;

    @Lob
    @Column(name = "profil_foto")
    private byte[] profilFoto;

    @Column(name = "isten_ayrılma_tarihi")
    private LocalDate cikis;

    @Column(name = "isten_ayrilma_nedeni")
    private String neden;
     
    @Column(name = "unvan", nullable = false, length = 50)
    @NotBlank(message = "Ünvan boş bırakılamaz")
    private String unvan;

    @OneToMany(mappedBy = "personel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Zimmet> zimmetler;
    
    public Personel() {
    }
    
    public Personel(Long id, @NotBlank(message = "Ad boş bırakılamaz") String ad,
            @NotBlank(message = "Soyadı boş bırakılamaz") String soyadi,
            @Pattern(regexp = "M|F", message = "Cinsiyet sadece M veya F harflerinden oluşabilir") @NotBlank(message = "Cinsiyet boş bırakılamaz") String cinsiyet,
            @NotNull(message = "Doğum tarihi boş olamaz") LocalDate dogumTarihi,
            @NotBlank(message = "Medeni durum boş bırakılamaz") @Pattern(regexp = "Evli|Bekar", message = "Medeni durum sadece 'Evli' veya 'Bekar' olabilir") String medeniDurum,
            @NotBlank(message = "TC no boş bırakılamaz") @Pattern(regexp = "\\d{11}", message = "TCKN 11 haneli sayı olmalıdır") String tckn,
            @NotNull(message = "Sicil numarası boş olamaz") @Pattern(regexp = "\\d+", message = "Sicil numarası sadece sayılardan oluşmalıdır") String sicilNo,
            String mezuniyet, @NotBlank(message = "Birim boş bırakılamaz") String birim,
            @NotBlank(message = "Pozisyon boş bırakılamaz") String pozisyon,
            @NotBlank(message = "Çalışma durumu boş bırakılamaz") @Pattern(regexp = "Evet|Hayır", message = "Çalışma durumu sadece 'Evet' veya 'Hayır' olabilir") String calismaDurumu,
            @NotNull(message = "İşe giriş tarihi boş olamaz") LocalDate giris, byte[] profilFoto, LocalDate cikis,
            String neden, @NotBlank(message = "Ünvan boş bırakılamaz") String unvan, List<Zimmet> zimmetler) {
        this.id = id;
        this.ad = ad;
        this.soyadi = soyadi;
        this.cinsiyet = cinsiyet;
        this.dogumTarihi = dogumTarihi;
        this.medeniDurum = medeniDurum;
        this.tckn = tckn;
        this.sicilNo = sicilNo;
        this.mezuniyet = mezuniyet;
        this.birim = birim;
        this.pozisyon = pozisyon;
        this.calismaDurumu = calismaDurumu;
        this.giris = giris;
        this.profilFoto = profilFoto;
        this.cikis = cikis;
        this.neden = neden;
        this.unvan = unvan;
        this.zimmetler = zimmetler;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public LocalDate getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(LocalDate dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getMedeniDurum() {
        return medeniDurum;
    }

    public void setMedeniDurum(String medeniDurum) {
        this.medeniDurum = medeniDurum;
    }

    public String getTckn() {
        return tckn;
    }

    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getMezuniyet() {
        return mezuniyet;
    }

    public void setMezuniyet(String mezuniyet) {
        this.mezuniyet = mezuniyet;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getCalismaDurumu() {
        return calismaDurumu;
    }

    public void setCalismaDurumu(String calismaDurumu) {
        this.calismaDurumu = calismaDurumu;
    }

    public LocalDate getGiris() {
        return giris;
    }

    public void setGiris(LocalDate giris) {
        this.giris= giris;
    }

    public byte[] getProfilFoto() {
        return profilFoto;
    }

    public void setProfilFoto(byte[] profilFoto) {
        this.profilFoto = profilFoto;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public void setPozisyon (String pozisyon) {
        this.pozisyon = pozisyon;
    }

    public LocalDate getCikis() {
        return cikis;
    }

    public void setCikis(LocalDate cikis) {
        this.cikis = cikis;
    }

    public String getNeden() {
        return neden;
    }

    public void setNeden(String neden) {
        this.neden = neden;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public List<Zimmet> getZimmetler() {
        return zimmetler;
    }

    public void setZimmetler(List<Zimmet> zimmetler) {
        this.zimmetler = zimmetler;
    }
}