package org.com.pti.example.piracema.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.com.pti.example.piracema.entities.StatusAntenna;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StatusAntennaRepository extends JpaRepository<StatusAntenna, Long> {
}