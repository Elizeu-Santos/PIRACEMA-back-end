package org.com.pti.example.piracema.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishNoPassesDTO {
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

    private Boolean recapture;

    private String comments;
}