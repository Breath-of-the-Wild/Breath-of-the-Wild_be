package com.breath_of_the_wild_be.repository.campingRepository;

import com.breath_of_the_wild_be.domain.Camping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

}
