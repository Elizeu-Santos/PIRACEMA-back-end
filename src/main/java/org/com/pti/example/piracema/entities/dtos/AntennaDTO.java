package org.com.pti.example.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaDTO {

    private Long id;

    private String antennaName;

    private LocalDateTime registryDate;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime deactivationDate;

    private List<PassNoAntennaDTO> passes;

    private List<StatusAntennaDTO> statusAntenna;
}