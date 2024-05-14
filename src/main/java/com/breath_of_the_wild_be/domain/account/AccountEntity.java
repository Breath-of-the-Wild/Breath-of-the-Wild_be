package com.breath_of_the_wild_be.domain.account;

import com.breath_of_the_wild_be.domain.common.BaseDateEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity extends BaseDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column(length = 100, nullable = false, unique = true)
  private String id;

  @Column(length = 500, nullable = false)
  private String password;
  @Column(length = 20)
  private String username;
  @Column(length = 20)
  private String birth;
  private String role;
}
