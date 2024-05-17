package com.breath_of_the_wild_be.repository.festivalRepository;

import com.breath_of_the_wild_be.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
}