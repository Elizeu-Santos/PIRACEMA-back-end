package org.com.pti.example.piracema.services;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.com.pti.example.piracema.entities.dtos.PassFileDTO;

@Service
public class ParseCSVService {

    public List<PassFileDTO> parse(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
                
        CsvToBean<PassFileDTO> csvToBean = new CsvToBeanBuilder(reader)
                                                .withSeparator(';')
                                                .withType(PassFileDTO.class)
                                                .build();

        List<PassFileDTO> dtos = csvToBean.parse();
       
        return dtos;
    }
}
