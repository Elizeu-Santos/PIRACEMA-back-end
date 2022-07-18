package org.com.pti.example.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassFormDTO {
    private Long fishId;
    private Long antennaId;
}
