package org.com.pti.example.piracema.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaNoPassesDTO {
    
    private Long id;

    private String antennaName;

    private LocalDateTime registryDate;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime deactivationDate;
}