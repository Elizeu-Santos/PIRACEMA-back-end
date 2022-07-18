package org.com.pti.example.piracema.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.com.pti.example.piracema.entities.dtos.AntennaNoPassesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

import org.com.pti.example.piracema.entities.Antenna;
import org.com.pti.example.piracema.entities.dtos.AntennaDTO;
import org.com.pti.example.piracema.services.AntennaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/antennas")
@Slf4j
public class AntennaController {

    private AntennaService antennaService;

    private ModelMapper modelMapper;

    public AntennaController(AntennaService antennaService, ModelMapper modelMapper) {
        this.antennaService = antennaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AntennaNoPassesDTO> create(@RequestBody Antenna antenna) {
        try {
            Antenna saveAntenna = antennaService.save(antenna);
            AntennaNoPassesDTO antennaDTO = modelMapper.map(saveAntenna, AntennaNoPassesDTO.class);
            return ResponseEntity.ok(antennaDTO);
        } catch (Exception e) {
            log.error("Falha na inserção de uma antena", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AntennaDTO>> getAll() {
        List<Antenna> antennas = antennaService.findAll();
        List<AntennaDTO> antennaDTOs = antennas.stream()
                .map(r -> modelMapper.map(r, AntennaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(antennaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antenna> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(antennaService.findById(id));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AntennaDTO> update(@PathVariable Long id, @RequestBody Antenna antenna){
        try {
            Antenna foundAntenna = antennaService.findById(id);
            modelMapper.map(antenna, foundAntenna);
            antennaService.save(foundAntenna);
            return ResponseEntity.ok(modelMapper.map(foundAntenna, AntennaDTO.class));
        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            antennaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante a remoção", e);
            return ResponseEntity.notFound().build();
        }
    }

}