package org.com.pti.example.piracema.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.com.pti.example.piracema.entities.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Optional<Fish> findTopByPitTagOrderByIdDesc(String pitTag);
    List<Fish> findAllByPitTagOrderByIdDesc(String pitTag);
    List<Fish> findAllByScientificName(String scientificName);
    List<Fish> findAllByCaptureSite(String captureSite);
    List<Fish> findAllByReleaseSite(String releaseSite);
    List<Fish> findAllByDnaSample(String dnaSample);
    Optional<Fish> findTopByDnaSampleOrderByIdDesc(String dnaSample);
    List<Fish> findAllByReleaseDate(LocalDateTime releaseDate);
}