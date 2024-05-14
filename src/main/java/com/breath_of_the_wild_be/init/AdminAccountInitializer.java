package com.breath_of_the_wild_be.init;


import com.breath_of_the_wild_be.domain.account.AccountEntity;
import com.breath_of_the_wild_be.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public AdminAccountInitializer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 이미 관리자 계정이 있는지 확인
        if (!accountRepository.existsById(1L)) {
            // 관리자 계정 생성
            AccountEntity adminAccount = AccountEntity.builder()
                    .id("admin")
                    .password("my1234")
                    .birth("1990-01-01")
                    .username("관리자")
                    .role("ADMIN")
                    .build();
            // 계정 저장
            accountRepository.save(adminAccount);
            System.out.println("Admin account created successfully.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}
