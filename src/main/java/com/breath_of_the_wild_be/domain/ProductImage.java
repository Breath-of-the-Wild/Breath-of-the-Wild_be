package com.breath_of_the_wild_be.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    private  String fileName;

    private int ord; //순번

    public void setOrd(int ord) {
        this.ord = ord;
    }
}
