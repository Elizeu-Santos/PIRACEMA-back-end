package org.com.pti.example.piracema.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.com.pti.example.piracema.entities.StatusAntenna;
import org.com.pti.example.piracema.entities.dtos.StatusAntennaDTO;
import org.com.pti.example.piracema.entities.dtos.StatusAntennaResponseDTO;
import org.com.pti.example.piracema.entities.dtos.StatusAntennaFormDTO;

import org.com.pti.example.piracema.services.StatusAntennaService;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statusantennas")
@Slf4j
public class StatusAntennaController {
    private StatusAntennaService statusAntennaService;
    private ModelMapper modelMapper;

    public StatusAntennaController(StatusAntennaService statusAntennaService, ModelMapper modelMapper) {
        this.statusAntennaService = statusAntennaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<StatusAntennaDTO> create (@RequestBody StatusAntennaFormDTO statusAntenna){
        try {
            StatusAntenna saveStatusAntenna = statusAntennaService.create(statusAntenna);
            return ResponseEntity.ok(modelMapper.map(saveStatusAntenna, StatusAntennaDTO.class));
        } catch (Exception e) {
            log.error("Falha na inserção de um StatusAntenna", e);
            
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StatusAntennaDTO>> getAll(){
        List<StatusAntenna> statusAntennas = statusAntennaService.findAll();
        List<StatusAntennaDTO> statusAntennaDTOs = statusAntennas.stream()
                                                                .map(r -> modelMapper.map(r, StatusAntennaDTO.class))
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(statusAntennaDTOs);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusAntennaDTO> getById(@PathVariable Long id) {
        try {
            StatusAntenna statusAntenna = statusAntennaService.findById(id);
            return ResponseEntity.ok(modelMapper.map(statusAntenna, StatusAntennaDTO.class));
        } catch(Exception e) {
            log.error("Status da Antena não encontrado!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusAntennaDTO> update(@PathVariable Long id, @RequestBody StatusAntenna statusAntenna){
        try {
            StatusAntenna foundStatusAntenna = statusAntennaService.findById(id);
            modelMapper.map(statusAntenna, foundStatusAntenna);
            statusAntennaService.save(foundStatusAntenna);
            return ResponseEntity.ok(modelMapper.map(foundStatusAntenna, StatusAntennaDTO.class));
        } catch(Exception e) {
            log.error("Falha durante a atualização", e);
            return ResponseEntity.notFound().build();
        }
    }
}