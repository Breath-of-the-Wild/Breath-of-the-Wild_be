package com.breath_of_the_wild_be.domain.common;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
abstract public class BaseInfoEntity extends BaseDateEntity {

  @Column(nullable = false)
  private Long creatorIdx;

  private Long updaterIdx;
}
