package org.com.pti.example.piracema.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class FishRecaptureDTO {

    private LocalDateTime registryDate;

    private Double totalLength;

    private String captureSite;

    private Double releaseWeight;

    private LocalDateTime releaseDate;

    private String releaseSite;

    private String dnaSample;

    private Boolean recapture = true;

    private List<PassNoFishDTO> passes;
}
