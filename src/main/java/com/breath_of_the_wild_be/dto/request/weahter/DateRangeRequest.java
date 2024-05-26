package com.breath_of_the_wild_be.dto.request.weahter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateRangeRequest {
    private String startDate;
    private String endDate;
}