package com.breath_of_the_wild_be.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_imagefile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageid;

    private String fileName;
    private String fileType;
    private String filePath;

    // Getters and Setters
}
