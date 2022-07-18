package org.com.pti.example.piracema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.com.pti.example.piracema.entities.Antenna;

@Repository
public interface AntennaRepository extends JpaRepository<Antenna, Long>{

}