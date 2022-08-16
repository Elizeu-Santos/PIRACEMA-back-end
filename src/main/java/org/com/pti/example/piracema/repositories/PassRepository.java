package org.com.pti.example.piracema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.com.pti.example.piracema.entities.Pass;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {
}