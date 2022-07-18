package org.com.pti.example.piracema.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "fishes")
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pittag", nullable = false)
    private String pitTag;
    
    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String scientificName;

    @Column(nullable = false)
    private String commonName;

    @Column(nullable = false)
    private Double standardLength;

    @Column(nullable = false)
    private Double totalLength;

    @Column(nullable = false)
    private String captureSite;

    @Column(nullable = false)
    private Double releaseWeight;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false)
    private String releaseSite;

    @Column(nullable = false)
    private String dnaSample;

    @Column(nullable = false)
    private Boolean recapture;

    @Column(nullable = false)
    private String comments;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany (mappedBy = "fish")
    private List<Pass> passes;

    @PrePersist
    public void create(){
        registryDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){updatedAt = LocalDateTime.now(); }

}