package org.com.pti.example.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaNoStatusAntennaDTO {

    private Long id;

    private String nameAntenna;

    private LocalDateTime registryDate;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime deactivationDate;

    private List<PassDTO> passes;
}