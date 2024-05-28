package com.breath_of_the_wild_be.repository.festivalRepository;

import com.breath_of_the_wild_be.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    Optional<Festival> findByContentid(String contentid);

    List<Festival> findByTitleContaining(String title);
    List<Festival> findByAddr1Containing(String addr1);
}