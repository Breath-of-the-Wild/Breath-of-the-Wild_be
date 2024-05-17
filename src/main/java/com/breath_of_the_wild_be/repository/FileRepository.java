package com.breath_of_the_wild_be.repository;

import com.breath_of_the_wild_be.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
