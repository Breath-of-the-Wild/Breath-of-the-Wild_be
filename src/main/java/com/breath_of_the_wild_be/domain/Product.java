package com.breath_of_the_wild_be.domain;
// 상품 Product는  ProductImage의 목록을 가지고 이를 관리하기는 기능을 함


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_product")
@Getter
@ToString(exclude = "imageList")

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;
    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    //추가2-------
    public void changeDel(boolean delFlag) {
        this.delFlag = delFlag;
    }

    //상품 수정 부분
    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeDesc(String desc){
        this.pdesc = desc;
    }

    public void changeName(String name){
        this.pname = name;
    }

    public void addImage(ProductImage image) {

        image.setOrd(this.imageList.size());
        imageList.add(image);
    }

    public void addImageString(String fileName){

        ProductImage productImage = ProductImage.builder()
                .fileName(fileName)
                .build();
        addImage(productImage);

    }

    //첨부파일 데이터들을 비우고 다시 ProductImage들을 추가하는 방식으로 구성
    public void clearList() {
        this.imageList.clear();
    }



}
