package com.my.jpaTest.service;

import com.my.jpaTest.entity.Entertainment;
import com.my.jpaTest.entity.GirlGroup;
import com.my.jpaTest.entity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
@Transactional
public class EnterService {
    @Autowired
    EntityManager em;

    public void insertAll() {
        Entertainment starship = Entertainment.builder()
                .e_id("starship")
                .e_name("스타쉽")
                .build();
        em.persist(starship);
        Entertainment yg = Entertainment.builder()
                .e_id("yg")
                .e_name("와이지")
                .build();
        em.persist(yg);

        GirlGroup ive = GirlGroup.builder()
                .g_id("ive")
                .g_name("아이브")
                .entertainment(starship)
                .build();
        em.persist(ive);

        GirlGroup bp = GirlGroup.builder()
                .g_id("blackPink")
                .g_name("블핑")
                .entertainment(yg)
                .build();
        em.persist(bp);

        IdolMember ahn = IdolMember.builder()
                .i_id("안유진")
                .i_name("유진")
                .girlGroup(ive)
                .build();
        em.persist(ahn);
        IdolMember jang = IdolMember.builder()
                .i_id("장원영")
                .i_name("원영")
                .girlGroup(ive)
                .build();
        em.persist(jang);
        IdolMember jen = IdolMember.builder()
                .i_id("제니")
                .i_name("째니")
                .girlGroup(bp)
                .build();
        em.persist(jen);
        IdolMember jisoo = IdolMember.builder()
                .i_id("지수")
                .i_name("지수다")
                .girlGroup(bp)
                .build();
        em.persist(jisoo);

        starship.getGirlGroups().add(ive);
        yg.getGirlGroups().add(bp);

        ive.getMembers().add(ahn);
        ive.getMembers().add(jang);
        bp.getMembers().add(jen);
        bp.getMembers().add(jisoo);

    }


}
