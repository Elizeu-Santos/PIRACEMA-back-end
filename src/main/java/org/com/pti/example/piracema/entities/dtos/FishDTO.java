package org.com.pti.example.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishDTO {

    private Long id;

    private String pitTag;

    private LocalDateTime registryDate;

    private String scientificName;

    private String commonName;

    private Double standardLength;

    private Double totalLength;

    private String captureSite;

    private Double releaseWeight;

    private LocalDateTime releaseDate;

    private String releaseSite;

    private String dnaSample;

    private Boolean recapture = false;

    private String comments;

    private List<PassNoFishDTO> passes;
}