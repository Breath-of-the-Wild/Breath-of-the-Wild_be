package com.breath_of_the_wild_be.dto.response;


import com.breath_of_the_wild_be.domain.Camping;
import org.springframework.data.domain.Page;

public class CampingResponseDTO {
    private Page<Camping> campings;
    private long totalCount;

    public CampingResponseDTO(Page<Camping> campings, long totalCount) {
        this.campings = campings;
        this.totalCount = totalCount;
    }

    public Page<Camping> getCampings() {
        return campings;
    }

    public void setCampings(Page<Camping> campings) {
        this.campings = campings;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
