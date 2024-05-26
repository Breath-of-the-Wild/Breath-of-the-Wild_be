package com.breath_of_the_wild_be.dto.response;


import com.breath_of_the_wild_be.domain.Festival;
import org.springframework.data.domain.Page;

public class FestivalResponseDTO {
    private Page<Festival> festivals;
    private long totalCount;

    public FestivalResponseDTO(Page<Festival> festivals, long totalCount) {
        this.festivals = festivals;
        this.totalCount = totalCount;
    }

    public Page<Festival> getFestivals() {
        return festivals;
    }

    public void setFestivals(Page<Festival> festivals) {
        this.festivals = festivals;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
