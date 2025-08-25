package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ContextServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ContextService contextService;

    @Test
    @DisplayName("1차 캐시테스트")
    void firstCash() {
        Member m = contextService.memberInsert();
        System.out.println("=======" + m);
    }

    @Test
    @DisplayName("데이터영속성보장테스트")
    void 데이터_영속성_보장_테스트() {
        Member a_1 = Member.builder()
                .memberId("hong")
                .name("홍길동")
                .build();
        Member b_1 = Member.builder()
                .memberId("hong")
                .name("홍길동")
                .build();
        System.out.println(a_1.equals(b_1));

        Member a = em.find(Member.class,"jang");
        Member b = em.find(Member.class,"jang");
        System.out.println(a.equals(b));
    }
}