package org.com.pti.example.piracema.services;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.com.pti.example.piracema.entities.Antenna;
import org.com.pti.example.piracema.entities.Fish;
import org.com.pti.example.piracema.entities.Pass;
import org.com.pti.example.piracema.entities.dtos.PassFileDTO;
import org.com.pti.example.piracema.entities.dtos.PassFormDTO;
import org.com.pti.example.piracema.repositories.PassRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class PassService {
    private final PassRepository passRepository;

    private final AntennaService antennaService;

    private final FishService fishService;
    private final ParseCSVService parseCSVService;

    public PassService(PassRepository passRepository, AntennaService antennaService, FishService fishService,
                       ParseCSVService parseCSVService) {
        this.passRepository = passRepository;
        this.antennaService = antennaService;
        this.fishService = fishService;
        this.parseCSVService = parseCSVService;
    }

    public Pass create(PassFormDTO passDTO) {
        Fish foundFish = fishService.findById(passDTO.getFishId());
        Antenna foundAntenna = antennaService.findById(passDTO.getAntennaId());

        Pass pass = new Pass();

        foundFish.getPasses().add(pass);
        pass.setFish(foundFish);

        foundAntenna.getPasses().add(pass);
        pass.setAntenna(foundAntenna);

        return passRepository.save(pass);
    }

    public Pass create(PassFileDTO passDTO) {
        Fish foundFish = fishService.findByPitTag(passDTO.getPitTag());
        Antenna foundAntenna = antennaService.findById(Long.valueOf(passDTO.getAntennaId()));
        
        Pass pass = new Pass();

        pass.setAntenna(foundAntenna);
        pass.setRegistryDate(passDTO.getRegistryDate());
        pass.setFish(foundFish);
        foundFish.getPasses().add(pass);
        

        foundAntenna.getPasses().add(pass);
        

        return passRepository.save(pass);
    }
    public List<Pass> upload(MultipartFile file) throws IOException {
        List<PassFileDTO> passesDTO = parseCSVService.parse(file);

        return passesDTO.stream().map(c -> create(c)).collect(Collectors.toList());
    }

    public Pass save(Pass pass) { return passRepository.save(pass); }

    public List<Pass> findAll() {
        return passRepository.findAll();
    }
    public Pass findById(Long id) {
        Optional<Pass> optPass = passRepository.findById(id);
        if (optPass.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optPass.get();
    }

    public void deleteById(Long id) {
        Pass pass = findById(id);
        passRepository.delete(pass);
    }
}