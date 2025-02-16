package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.PersonelDTO;
import com.ikenvanter.envanter.dto.ZimmetDTO;
import com.ikenvanter.envanter.entity.Personel;
import com.ikenvanter.envanter.repo.PersonelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonelService {

    @Autowired
    private PersonelRepo personelRepo;

    private PersonelDTO convertToDto(Personel personel) {
        if (personel == null) {
            return null;
        }
        
        PersonelDTO personelDTO = new PersonelDTO();
        personelDTO.setId(personel.getId());
        personelDTO.setAd(personel.getAd());
        personelDTO.setSoyadi(personel.getSoyadi());
        personelDTO.setCinsiyet(personel.getCinsiyet());
        personelDTO.setDogumTarihi(personel.getDogumTarihi());
        personelDTO.setMedeniDurum(personel.getMedeniDurum());
        personelDTO.setTckn(personel.getTckn());
        personelDTO.setSicilNo(personel.getSicilNo());
        personelDTO.setMezuniyet(personel.getMezuniyet());
        personelDTO.setBirim(personel.getBirim());
        personelDTO.setPozisyon(personel.getPozisyon());
        personelDTO.setCalismaDurumu(personel.getCalismaDurumu());
        personelDTO.setGiris(personel.getGiris());
        personelDTO.setProfilFoto(personel.getProfilFoto());
        personelDTO.setCikis(personel.getCikis());
        personelDTO.setNeden(personel.getNeden());
        personelDTO.setUnvan(personel.getUnvan());

        if (personel.getZimmetler() != null && !personel.getZimmetler().isEmpty()) {
            personelDTO.setZimmetler(personel.getZimmetler().stream()
                    .map(zimmet -> {
                        ZimmetDTO zimmetDTO = new ZimmetDTO();
                        zimmetDTO.setId(zimmet.getId());
                        zimmetDTO.setEnvanterId(zimmet.getEnvanter().getId());
                        zimmetDTO.setVerilmeTarihi(zimmet.getVerilmeTarihi());
                        zimmetDTO.setGeriAlinmaTarihi(zimmet.getGeriAlinmaTarihi());
                        return zimmetDTO;
                    }).collect(Collectors.toList()));
        }

        return personelDTO;
    }

    private Personel convertToEntity(PersonelDTO personelDTO) {
        if (personelDTO == null) {
            return null;
        }

        Personel personel = new Personel();
        personel.setId(personelDTO.getId());
        personel.setAd(personelDTO.getAd());
        personel.setSoyadi(personelDTO.getSoyadi());
        personel.setCinsiyet(personelDTO.getCinsiyet());
        personel.setDogumTarihi(personelDTO.getDogumTarihi());
        personel.setMedeniDurum(personelDTO.getMedeniDurum());
        personel.setTckn(personelDTO.getTckn());
        personel.setSicilNo(personelDTO.getSicilNo());
        personel.setMezuniyet(personelDTO.getMezuniyet());
        personel.setBirim(personelDTO.getBirim());
        personel.setPozisyon(personelDTO.getPozisyon());
        personel.setCalismaDurumu(personelDTO.getCalismaDurumu());
        personel.setGiris(personelDTO.getGiris());
        personel.setProfilFoto(personelDTO.getProfilFoto());
        personel.setCikis(personelDTO.getCikis());
        personel.setNeden(personelDTO.getNeden());
        personel.setUnvan(personelDTO.getUnvan());

        return personel;
    }

    public PersonelDTO createPersonel(PersonelDTO personelDTO) {
        Personel personel = convertToEntity(personelDTO);
        personel = personelRepo.save(personel);
        return convertToDto(personel);
    }

    public PersonelDTO updatePersonel(Long id, PersonelDTO personelDTO) {
        Personel personel = personelRepo.findById(id).orElseThrow(() -> new RuntimeException("Personel not found"));

        personel.setAd(personelDTO.getAd());
        personel.setSoyadi(personelDTO.getSoyadi());
        personel.setCinsiyet(personelDTO.getCinsiyet());
        personel.setDogumTarihi(personelDTO.getDogumTarihi());
        personel.setMedeniDurum(personelDTO.getMedeniDurum());
        personel.setTckn(personelDTO.getTckn());
        personel.setSicilNo(personelDTO.getSicilNo());
        personel.setMezuniyet(personelDTO.getMezuniyet());
        personel.setBirim(personelDTO.getBirim());
        personel.setPozisyon(personelDTO.getPozisyon());
        personel.setCalismaDurumu(personelDTO.getCalismaDurumu());
        personel.setGiris(personelDTO.getGiris());
        personel.setProfilFoto(personelDTO.getProfilFoto());
        personel.setCikis(personelDTO.getCikis());
        personel.setNeden(personelDTO.getNeden());
        personel.setUnvan(personelDTO.getUnvan());

        personel = personelRepo.save(personel);
        return convertToDto(personel);
    }

    public PersonelDTO getPersonelById(Long id) {
        Personel personel = personelRepo.findById(id).orElseThrow(() -> new RuntimeException("Personel not found"));
        return convertToDto(personel);
    }

    public List<PersonelDTO> getAllPersonel() {
        return personelRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void deletePersonel(Long id) {
        personelRepo.deleteById(id);
    }

    public List<PersonelDTO> findBySicilNoContainingOrAdiContaining(String sicilNo, String ad) {
        List<Personel> personelList = personelRepo.findBySicilNoContainingOrAdiContaining(sicilNo, ad);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PersonelDTO> findByAdiContaining(String ad) {
        List<Personel> personelList = personelRepo.findByAdiContaining(ad);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PersonelDTO> findBySoyadiContaining(String soyadi) {
        List<Personel> personelList = personelRepo.findBySoyadiContaining(soyadi);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PersonelDTO> findByTckn(String tckn) {
        List<Personel> personelList = personelRepo.findByTckn(tckn);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PersonelDTO> findByBirim(String birim) {
        List<Personel> personelList = personelRepo.findByBirim(birim);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<PersonelDTO> findBySicilNo(String sicilNo) {
        List<Personel> personelList = personelRepo.findBySicilNo(sicilNo);
        return personelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
