package com.ikenvanter.envanter.service;

import com.ikenvanter.envanter.dto.EnvanterDTO;
import com.ikenvanter.envanter.entity.Envanter;
import com.ikenvanter.envanter.repo.EnvanterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvanterService {

    @Autowired
    private EnvanterRepo envanterRepo;

    public List<EnvanterDTO> getAllEnvanter() {
        return envanterRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public EnvanterDTO getEnvanterById(Long id) {
        Optional<Envanter> envanter = envanterRepo.findById(id);
        return envanter.map(this::convertToDTO).orElse(null);
    }

    public EnvanterDTO saveEnvanter(EnvanterDTO envanterDTO) {
        Envanter envanter = convertToEntity(envanterDTO);
        Envanter savedEnvanter = envanterRepo.save(envanter);
        return convertToDTO(savedEnvanter);
    }

    public EnvanterDTO updateEnvanter(Long id, EnvanterDTO envanterDTO) {
        Optional<Envanter> envanterOpt = envanterRepo.findById(id);
        if (envanterOpt.isPresent()) {
            Envanter envanter = envanterOpt.get();
            envanter.setTipi(envanterDTO.getTipi());
            envanter.setMarka(envanterDTO.getMarka());
            envanter.setModel(envanterDTO.getModel());
            envanter.setSeriNumarasi(envanterDTO.getSeriNumarasi());
            envanter.setGirisTarihi(envanterDTO.getGirisTarihi());
            envanter.setStatu(envanterDTO.getStatu());
            envanter.setToplamSayi(envanterDTO.getToplamSayi());
            envanter.setKullanimdaOlanSayi(envanterDTO.getKullanimdaOlanSayi());
            envanter.setKullanimdaOlmayanSayi(envanterDTO.getKullanimdaOlmayanSayi());
            Envanter updatedEnvanter = envanterRepo.save(envanter);
            return convertToDTO(updatedEnvanter);
        } else {
            return null;
        }
    }

    public void deleteEnvanter(Long id) {
        envanterRepo.deleteById(id);
    }

    private EnvanterDTO convertToDTO(Envanter envanter) {
        EnvanterDTO envanterDTO = new EnvanterDTO();
        envanterDTO.setId(envanter.getId());
        envanterDTO.setTipi(envanter.getTipi());
        envanterDTO.setMarka(envanter.getMarka());
        envanterDTO.setModel(envanter.getModel());
        envanterDTO.setSeriNumarasi(envanter.getSeriNumarasi());
        envanterDTO.setGirisTarihi(envanter.getGirisTarihi());
        envanterDTO.setStatu(envanter.getStatu());
        envanterDTO.setToplamSayi(envanter.getToplamSayi());
        envanterDTO.setKullanimdaOlanSayi(envanter.getKullanimdaOlanSayi());
        envanterDTO.setKullanimdaOlmayanSayi(envanter.getKullanimdaOlmayanSayi());
        return envanterDTO;
    }

    private Envanter convertToEntity(EnvanterDTO envanterDTO) {
        Envanter envanter = new Envanter();
        envanter.setId(envanterDTO.getId());
        envanter.setTipi(envanterDTO.getTipi());
        envanter.setMarka(envanterDTO.getMarka());
        envanter.setModel(envanterDTO.getModel());
        envanter.setSeriNumarasi(envanterDTO.getSeriNumarasi());
        envanter.setGirisTarihi(envanterDTO.getGirisTarihi());
        envanter.setStatu(envanterDTO.getStatu());
        envanter.setToplamSayi(envanterDTO.getToplamSayi());
        envanter.setKullanimdaOlanSayi(envanterDTO.getKullanimdaOlanSayi());
        envanter.setKullanimdaOlmayanSayi(envanterDTO.getKullanimdaOlmayanSayi());
        return envanter;
    }
    public List<EnvanterDTO> searchEnvanter(String tipi, String marka, LocalDate startDate, LocalDate endDate) {
        List<Envanter> envanterList = envanterRepo.findByTipiContainingOrMarkaContaining(tipi, marka);
        return envanterList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EnvanterDTO> findByTipi(String tipi) {
        return envanterRepo.findByTipi(tipi).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EnvanterDTO> findByMarka(String marka) {
        return envanterRepo.findByMarkaContaining(marka).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EnvanterDTO> findByGirisTarihiBetween(LocalDate startDate, LocalDate endDate) {
        return envanterRepo.findByGirisTarihiBetween(startDate, endDate).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EnvanterDTO> findKullanimdaOlanlar() {
        return envanterRepo.findKullanimdaOlanlar().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<EnvanterDTO> findKullanimdaOlmayanlar() {
        return envanterRepo.findKullanimdaOlmayanlar().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
