package org.com.pti.example.piracema.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.com.pti.example.piracema.entities.Antenna;
import org.com.pti.example.piracema.repositories.AntennaRepository;

@Service
@Transactional
public class AntennaService {

    private final AntennaRepository antennaRepository;

    public AntennaService(AntennaRepository antennaRepository) {this.antennaRepository = antennaRepository; }

    public Antenna save(Antenna antenna) { return antennaRepository.save(antenna); }

    public List<Antenna> findAll() { return antennaRepository.findAll(); }

    public Antenna findById(Long id) {
        Optional<Antenna> optAntenna = antennaRepository.findById(id);

        if (optAntenna.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optAntenna.get();
    }
    public void deleteById(Long id) {
        Antenna antenna = this.findById(id);
        antennaRepository.delete(antenna);
    }
}