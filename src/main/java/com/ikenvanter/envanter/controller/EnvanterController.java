package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.EnvanterDTO;
import com.ikenvanter.envanter.service.EnvanterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/public/envanter")
public class EnvanterController {

    @Autowired
    private EnvanterService envanterService;

    @GetMapping("/list")
    public ResponseEntity<List<EnvanterDTO>> getAllEnvanter() {
        List<EnvanterDTO> envanterler = envanterService.getAllEnvanter();
        return new ResponseEntity<>(envanterler, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvanterDTO> getEnvanterById(@PathVariable Long id) {
        EnvanterDTO envanter = envanterService.getEnvanterById(id);
        if (envanter != null) {
            return new ResponseEntity<>(envanter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path= "/create", produces = { "application/json" }) 
    public ResponseEntity<EnvanterDTO> createEnvanter(@RequestBody EnvanterDTO envanterDTO) {
        System.out.println("envanter çalıştı");
        EnvanterDTO savedEnvanter = envanterService.saveEnvanter(envanterDTO);
        
        return new ResponseEntity<>(savedEnvanter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvanterDTO> updateEnvanter(@PathVariable Long id, @RequestBody EnvanterDTO envanterDTO) {
        EnvanterDTO updatedEnvanter = envanterService.updateEnvanter(id, envanterDTO);
        if (updatedEnvanter != null) {
            return new ResponseEntity<>(updatedEnvanter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnvanter(@PathVariable Long id) {
        envanterService.deleteEnvanter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EnvanterDTO>> searchEnvanter(
            @RequestParam(value = "tipi", required = false) String tipi,
            @RequestParam(value = "marka", required = false) String marka,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<EnvanterDTO> envanterList = null;

        if (tipi != null && marka != null) {
            envanterList = envanterService.searchEnvanter(tipi, marka, startDate, endDate);
        } else if (tipi != null) {
            envanterList = envanterService.findByTipi(tipi);
        } else if (marka != null) {
            envanterList = envanterService.findByMarka(marka);
        } else if (startDate != null && endDate != null) {
            envanterList = envanterService.findByGirisTarihiBetween(startDate, endDate);
        } else if (tipi != null || marka != null) {
            envanterList = envanterService.searchEnvanter(tipi, marka, null, null);
        }

        return new ResponseEntity<>(envanterList, HttpStatus.OK);
    }
}
