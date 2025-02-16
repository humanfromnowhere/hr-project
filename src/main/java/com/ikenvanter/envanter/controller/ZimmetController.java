package com.ikenvanter.envanter.controller;

import com.ikenvanter.envanter.dto.ZimmetDTO;
import com.ikenvanter.envanter.service.ZimmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/zimmet")
public class ZimmetController {

    @Autowired
    private ZimmetService zimmetService;

    @GetMapping("/listz")
    public ResponseEntity<List<ZimmetDTO>> getAllZimmetler() {
        List<ZimmetDTO> zimmetler = zimmetService.getAllZimmetler();
        return ResponseEntity.ok(zimmetler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZimmetDTO> getZimmetById(@PathVariable Long id) {
        ZimmetDTO zimmetDTO = zimmetService.getZimmetById(id);
        if (zimmetDTO != null) {
            return ResponseEntity.ok(zimmetDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createz")
    public ResponseEntity<ZimmetDTO> createZimmet(@RequestBody ZimmetDTO zimmetDTO) {
        ZimmetDTO savedZimmet = zimmetService.saveZimmet(zimmetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedZimmet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZimmetDTO> updateZimmet(@PathVariable Long id, @RequestBody ZimmetDTO zimmetDTO) {
        ZimmetDTO updatedZimmet = zimmetService.updateZimmet(id, zimmetDTO);
        if (updatedZimmet != null) {
            return ResponseEntity.ok(updatedZimmet);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteZimmet(@PathVariable Long id) {
        zimmetService.deleteZimmet(id);
        return ResponseEntity.noContent().build();
    }
}
