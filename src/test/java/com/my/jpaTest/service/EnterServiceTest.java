package com.my.jpaTest.service;

import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EnterServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    EnterService enterService;


    @Test
    @DisplayName("")
    void 문제1() {
        IdolMember jisoo = em.find(IdolMember.class, "지수");
        String groupName = jisoo.getGirlGroup().getG_name();
        String enterName = jisoo.getGirlGroup().getEntertainment().getE_name();
        System.out.println("group : " + groupName + "Enter : " + enterName);
    }

    @Test
    @DisplayName("")
    void 문제2() {
        GirlGroup group = em.find(GirlGroup.class, "blackPink");
        group.getMembers().forEach(x-> System.out.println(x.getI_name()));
    }
}