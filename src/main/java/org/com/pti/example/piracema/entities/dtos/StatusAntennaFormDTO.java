package org.com.pti.example.piracema.entities.dtos;

import java.time.LocalDateTime;

import javax.xml.bind.Marshaller.Listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StatusAntennaFormDTO {

    private Boolean status;

    private LocalDateTime statusChangeDate;

    private Long antennaId;

}
