package org.com.pti.example.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusAntennaResponseDTO {

    private Long id;

    private Boolean status;

    private AntennaDTO antenna;
    
}

