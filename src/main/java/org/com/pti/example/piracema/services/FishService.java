package org.com.pti.example.piracema.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.com.pti.example.piracema.entities.Fish;
import org.com.pti.example.piracema.repositories.FishRepository;

@Service
@Transactional
public class FishService {

    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository) { this.fishRepository = fishRepository; }

    public Fish save(Fish fish, Boolean recapture) {

        Optional<Fish> optionalFish = fishRepository.findTopByPitTagOrderByIdDesc(fish.getPitTag());

        Optional<Fish> optionalFishDna = fishRepository.findTopByDnaSampleOrderByIdDesc(fish.getDnaSample());

        if(optionalFish.isPresent()) {
            Fish foundedFish = optionalFish.get();

            if(!recapture){
                if (foundedFish.getPitTag().equals(fish.getPitTag()) && !fish.getRecapture()){
                    throw new DataIntegrityViolationException("Este peixe já existe, porém não foi informada sua recaptura!");
                }
            }
        }

        return fishRepository.save(fish);
    }

    public List<Fish> findAll() { return fishRepository.findAll(); }

    public Fish findById(Long id) {
        Optional<Fish> optFish = fishRepository.findById(id);

        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }
    public Fish findByPitTag(String pitTag) {
        Optional<Fish> optFish = fishRepository.findTopByPitTagOrderByIdDesc(pitTag);
        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public List<Fish> findAllByPitTag(String pitTag) {
        List<Fish> fishList = fishRepository.findAllByPitTagOrderByIdDesc(pitTag);
        if (fishList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByScientificName(String scientificName) {

        List<Fish> fishList = fishRepository.findAllByScientificName(scientificName);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByCaptureSite(String captureSite) {
        List<Fish> fishList = fishRepository.findAllByCaptureSite(captureSite);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByReleaseDate(LocalDateTime releaseDate) {
        List<Fish> fishList = fishRepository.findAllByReleaseDate (releaseDate);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByReleaseSite(String releaseSite) {
        List<Fish> fishList = fishRepository.findAllByReleaseSite(releaseSite);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByDnaSample(String dnaSample) {
        List<Fish> fishList = fishRepository.findAllByDnaSample(dnaSample);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public void deleteById(Long id) {
        Fish fish = findById(id);
        fishRepository.delete(fish);
    }

    
}
