package org.com.pti.example.piracema.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.com.pti.example.piracema.entities.Antenna;
import org.com.pti.example.piracema.entities.StatusAntenna;
import org.com.pti.example.piracema.entities.dtos.StatusAntennaDTO;
import org.com.pti.example.piracema.entities.dtos.StatusAntennaFormDTO;
import org.com.pti.example.piracema.repositories.StatusAntennaRepository;
import org.modelmapper.ModelMapper;

@Service
@Transactional
public class StatusAntennaService {

    private final StatusAntennaRepository statusAntennaRepository;
    
    private final AntennaService antennaService;

    private ModelMapper modelMapper;
    
    public StatusAntennaService(StatusAntennaRepository statusAntennaRepository, AntennaService antennaService) {
        this.statusAntennaRepository = statusAntennaRepository;
        this.antennaService = antennaService;
    }

    public StatusAntenna create(StatusAntennaFormDTO statusAntennaDTO) {
        Antenna foundAntenna = antennaService.findById(statusAntennaDTO.getAntennaId());

        StatusAntenna statusAntenna = new StatusAntenna();

        statusAntenna.setStatusChangeDate(statusAntennaDTO.getStatusChangeDate());
        statusAntenna.setStatus(statusAntennaDTO.getStatus());
        statusAntenna.setAntenna(foundAntenna);
        foundAntenna.getStatusAntenna().add(statusAntenna);
       
        

        return statusAntennaRepository.save(statusAntenna);
    }


    public List<StatusAntenna> findAll() {
        return statusAntennaRepository.findAll();
    }

    public StatusAntenna findById(Long id) {
        Optional<StatusAntenna> optStatusAntenna = statusAntennaRepository.findById(id);
        if (optStatusAntenna.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optStatusAntenna.get();
    }

    public void save(StatusAntenna foundStatusAntenna) {
    }
}