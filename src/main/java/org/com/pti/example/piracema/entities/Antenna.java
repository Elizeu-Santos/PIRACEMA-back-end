package org.com.pti.example.piracema.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Antenna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String antennaName;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private LocalDateTime installationDate;

    private LocalDateTime deactivationDate;

    @OneToMany(mappedBy = "antenna", cascade = {CascadeType.ALL})
    private List<Pass> passes;

    @OneToMany(mappedBy = "antenna", cascade = {CascadeType.ALL})
    private List<StatusAntenna> statusAntenna;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
}