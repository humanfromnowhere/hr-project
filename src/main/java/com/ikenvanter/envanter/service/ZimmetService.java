package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.ZimmetDTO;
import com.ikenvanter.envanter.entity.Zimmet;
import com.ikenvanter.envanter.entity.Personel;
import com.ikenvanter.envanter.entity.Envanter;
import com.ikenvanter.envanter.repo.ZimmetRepo;
import com.ikenvanter.envanter.repo.PersonelRepo;
import com.ikenvanter.envanter.repo.EnvanterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZimmetService {

    @Autowired
    private ZimmetRepo zimmetRepo;

    @Autowired
    private PersonelRepo personelRepo;

    @Autowired
    private EnvanterRepo envanterRepo;

    public List<ZimmetDTO> getAllZimmetler() {
        List<Zimmet> zimmetler = zimmetRepo.findAll();
        return zimmetler.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ZimmetDTO getZimmetById(Long id) {
        Optional<Zimmet> zimmet = zimmetRepo.findById(id);
        return zimmet.map(this::convertToDto).orElse(null);
    }

    public ZimmetDTO saveZimmet(ZimmetDTO zimmetDTO) {
        Zimmet zimmet = convertToEntity(zimmetDTO);
        zimmet = zimmetRepo.save(zimmet);
        return convertToDto(zimmet);
    }

    public ZimmetDTO updateZimmet(Long id, ZimmetDTO zimmetDTO) {
        Optional<Zimmet> zimmetOptional = zimmetRepo.findById(id);
        if (zimmetOptional.isPresent()) {
            Zimmet zimmet = convertToEntity(zimmetDTO);
            zimmet.setId(id);
            zimmet = zimmetRepo.save(zimmet);
            return convertToDto(zimmet);
        }
        return null;
    }

    public void deleteZimmet(Long id) {
        zimmetRepo.deleteById(id);
    }

    private ZimmetDTO convertToDto(Zimmet zimmet) {
        return new ZimmetDTO(
                zimmet.getId(), 
                zimmet.getPersonel().getId(), 
                zimmet.getEnvanter().getId(), 
                zimmet.getVerilmeTarihi(), 
                zimmet.getGeriAlinmaTarihi(), 
                zimmet.getTeslimEden(), 
                zimmet.getTeslimAlan());
    }

    private Zimmet convertToEntity(ZimmetDTO zimmetDTO) {
        Zimmet zimmet = new Zimmet();
        zimmet.setId(zimmetDTO.getId());

        Personel personel = personelRepo.findById(zimmetDTO.getPersonelId())
                .orElseThrow(() -> new IllegalArgumentException("Personel ID geçersiz: " + zimmetDTO.getPersonelId()));
        zimmet.setPersonel(personel);

        Envanter envanter = envanterRepo.findById(zimmetDTO.getEnvanterId())
                .orElseThrow(() -> new IllegalArgumentException("Envanter ID geçersiz: " + zimmetDTO.getEnvanterId()));
        zimmet.setEnvanter(envanter);

        zimmet.setVerilmeTarihi(zimmetDTO.getVerilmeTarihi());
        zimmet.setGeriAlinmaTarihi(zimmetDTO.getGeriAlinmaTarihi());
        zimmet.setTeslimEden(zimmetDTO.getTeslimEden());
        zimmet.setTeslimAlan(zimmetDTO.getTeslimAlan());
        return zimmet;
    }
}
