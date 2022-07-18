package org.com.pti.example.piracema.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.pti.example.piracema.entities.Fish;
import org.com.pti.example.piracema.entities.dtos.FishDTO;
import org.com.pti.example.piracema.entities.dtos.FishNoPassesDTO;
import org.com.pti.example.piracema.entities.dtos.FishRecaptureDTO;
import org.com.pti.example.piracema.services.FishService;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fishes")
@Slf4j
public class FishController {

    private FishService fishService;

    private ModelMapper modelMapper;

    private Boolean recapture = false;

    public FishController(ModelMapper modelMapper, FishService fishService) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<FishNoPassesDTO> create(@RequestBody Fish fish) {
        try {
            Fish savedFish = fishService.save(fish, recapture);
            FishNoPassesDTO fishDTO = modelMapper.map(savedFish, FishNoPassesDTO.class);
        return ResponseEntity.ok(fishDTO);
        } catch(Exception e) {
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/many")
    public ResponseEntity<List<FishNoPassesDTO>> create(@RequestBody List<Fish> fishes){
        try{
            List<FishNoPassesDTO> fishNoPassesDTO =  fishes
                    .stream()
                    .map(f -> modelMapper
                            .map(fishService
                                    .save(f, recapture), FishNoPassesDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(fishNoPassesDTO);

        }catch (Exception e){
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FishDTO>> findAll (){
        List<Fish> fishes;
        fishes = fishService.findAll();

        List<FishDTO> fishesDTO = fishes
                                    .stream()
                                    .map(c -> modelMapper
                                    .map(c, FishDTO.class))
                                    .collect(Collectors
                                    .toList());
        return ResponseEntity.ok(fishesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FishDTO> find(@PathVariable Long id) {
        try {
            Fish fish = fishService.findById(id);
            return ResponseEntity.ok(modelMapper.map(fish, FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pitTag/{pitTag}")
    public ResponseEntity<FishDTO> findByPitTag(@PathVariable String pitTag) {
        try {
            Fish fish = fishService.findByPitTag(pitTag);
            return ResponseEntity.ok(modelMapper.map(fish, FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pitTags/{pitTag}")
    public ResponseEntity<List<FishDTO>> findAllByPitTag(@PathVariable String pitTag) {
        try {
            List<Fish> fishList = fishService.findAllByPitTag(pitTag);

            List<FishDTO> fishDTOList = fishList.stream()
                                                .map(fish -> modelMapper
                                                .map(fish, FishDTO.class))
                                                .collect(Collectors.toList());
            return ResponseEntity.ok(fishDTOList);
        } catch(Exception e) {
            log.error("Peixe não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/scientificNames/{scientificName}")
    public ResponseEntity<List<FishDTO>> findAllByScientificName(@PathVariable String scientificName) {
        try {
            List<Fish> fishList = fishService.findAllByScientificName(scientificName);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);
        } catch(Exception e) {
            log.error("Nome científico não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/captureSites/{captureSite}")
    public ResponseEntity<List<FishDTO>> findAllByCaptureSite(@PathVariable String captureSite) {
        try {
            List<Fish> fishList = fishService.findAllByCaptureSite(captureSite);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Não há registro de captura de peixes no local de captura informado!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/releaseSites/{releaseSite}")
    public ResponseEntity<List<FishDTO>> findAllByReleaseLocation(@PathVariable String releaseSite) {
        try {
            List<Fish> fishList = fishService.findAllByReleaseSite(releaseSite);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Não há registro de soltura de peixes no local de soltura informado!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/releaseDate/{releaseDate}")
    public ResponseEntity<List<FishDTO>> findAllByReleaseDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    @PathVariable LocalDateTime releaseDate) {
        try {
            List<Fish> fishList = fishService.findAllByReleaseDate(releaseDate);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper 
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Não há registro de soltura de peixes no local de soltura informado!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dnaSamples/{dnaSample}")
    public ResponseEntity<List<FishDTO>> findAllByDnaSample(@PathVariable String dnaSample) {
        try {
            List<Fish> fishList = fishService.findAllByDnaSample(dnaSample);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Não há nos registros a amostra de DNA informada!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable Long id,
                                          @RequestBody Fish fish) {
        recapture=true;
        try {
            Fish foundFish = fishService.findById(id);
            modelMapper.map(fish, foundFish);
            List<Fish> fishNames = fishService.findAllByScientificName(foundFish.getScientificName());

            if (!foundFish.getScientificName().isEmpty()){
                fishNames.forEach((f) ->{
                    f.setScientificName(foundFish.getScientificName());
                });
            }
            if (!foundFish.getCommonName().isEmpty()){
                fishNames.forEach((f) -> {
                    f.setCommonName(foundFish.getCommonName());
                });
            }

            List<Fish> fishList = fishService.findAllByPitTag(foundFish.getPitTag());

            if(!foundFish.getScientificName().isEmpty()){
                fishList.forEach((f) -> {
                    f.setScientificName(foundFish.getScientificName());
                });
            }
            if(!foundFish.getCommonName().isEmpty()){
                fishList.forEach((f) -> {
                    f.setCommonName(foundFish.getCommonName());
                });
            }
            if (!foundFish.getTotalLength().toString().isEmpty()){
                fishList.forEach((f) -> {
                    f.setTotalLength(foundFish.getTotalLength());
                });
            }

            fishService.save(foundFish, recapture);

            recapture=false;
            return ResponseEntity.ok(modelMapper.map(foundFish, FishDTO.class));
        } catch(Exception e) {
            log.error("Falha durante a atualização", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            fishService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante a remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}