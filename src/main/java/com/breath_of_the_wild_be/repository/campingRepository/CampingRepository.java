package com.breath_of_the_wild_be.repository.campingRepository;

import com.breath_of_the_wild_be.domain.Camping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

    Optional<Camping> findByContentId(Long contentId);

    List<Camping> findByFacltNmContaining(String facltNm);

    List<Camping> findByAddr1Containing(String addr1);

    List<Camping> findByDoNm(String doNm);
}