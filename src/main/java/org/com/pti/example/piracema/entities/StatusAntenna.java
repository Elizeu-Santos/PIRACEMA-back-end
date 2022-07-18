package org.com.pti.example.piracema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StatusAntenna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime registryDate;

    @Column(nullable = false)
    private Boolean status;

    
    private LocalDateTime statusChangeDate;

    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAntenna")
    private Antenna antenna;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void update(){
        updatedAt = LocalDateTime.now();
    }
}