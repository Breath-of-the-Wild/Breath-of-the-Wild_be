package com.breath_of_the_wild_be.service.imageFileService;

import com.breath_of_the_wild_be.domain.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageFileService {

    ImageFile saveImage(MultipartFile file)  throws IOException;

    byte[] getImage(String fileName) throws IOException;
}
