package com.breath_of_the_wild_be.repository.imageFileRepository;

import com.breath_of_the_wild_be.domain.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
}
