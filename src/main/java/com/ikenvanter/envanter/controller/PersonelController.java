package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.PersonelDTO;
import com.ikenvanter.envanter.service.PersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/personel")
public class PersonelController {

    @Autowired
    private PersonelService personelService;

    @PostMapping(path ="/createp", produces = { "application/json" })
    public ResponseEntity<PersonelDTO> createPersonel(@RequestBody PersonelDTO personelDTO) {
        PersonelDTO createdPersonel = personelService.createPersonel(personelDTO);
        return new ResponseEntity<>(createdPersonel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonelDTO> updatePersonel(@PathVariable Long id, @RequestBody PersonelDTO personelDTO) {
        PersonelDTO updatedPersonel = personelService.updatePersonel(id, personelDTO);
        return ResponseEntity.ok(updatedPersonel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonelDTO> getPersonelById(@PathVariable Long id) {
        PersonelDTO personelDTO = personelService.getPersonelById(id);
        return ResponseEntity.ok(personelDTO);
    }

    @GetMapping("/listp")
    public ResponseEntity<List<PersonelDTO>> getAllPersonel() {
        List<PersonelDTO> personelList = personelService.getAllPersonel();
        return ResponseEntity.ok(personelList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonel(@PathVariable Long id) {
        personelService.deletePersonel(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<PersonelDTO>> searchPersonel(
            @RequestParam(value = "sicilNo", required = false) String sicilNo,
            @RequestParam(value = "ad", required = false) String ad,
            @RequestParam(value = "soyadi", required = false) String soyadi,
            @RequestParam(value = "tckn", required = false) String tckn,
            @RequestParam(value = "birim", required = false) String birim) {

        List<PersonelDTO> personelList = new ArrayList<>();

        if (sicilNo != null && !sicilNo.isEmpty()) {
            personelList = personelService.findBySicilNo(sicilNo);
        } else if (sicilNo != null && !sicilNo.isEmpty() && ad != null && !ad.isEmpty()) {
            personelList = personelService.findBySicilNoContainingOrAdiContaining(sicilNo, ad);
        } else if (ad != null && !ad.isEmpty()) {
            personelList = personelService.findByAdiContaining(ad);
        } else if (soyadi != null && !soyadi.isEmpty()) {
            personelList = personelService.findBySoyadiContaining(soyadi);
        } else if (tckn != null && !tckn.isEmpty()) {
            personelList = personelService.findByTckn(tckn);
        } else if (birim != null && !birim.isEmpty()) {
            personelList = personelService.findByBirim(birim);
        }

        if (personelList.isEmpty()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }

        return ResponseEntity.ok(personelList);
    }
}


