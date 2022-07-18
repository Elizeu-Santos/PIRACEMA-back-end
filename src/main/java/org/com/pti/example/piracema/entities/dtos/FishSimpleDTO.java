package org.com.pti.example.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishSimpleDTO {
    private Long id;

    private String pitTag;

    private String scientificName;

    private String commonName;
}
