package com.my.jpaTest.quiz;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository repository;

    @Test
    @Transactional
    @DisplayName("Given/When/Then으로테스트하기")
    void assertThatTest() {
//        신규 데이터 추가 테스트
        //        Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
//        When
        repository.save(jin);
//        Then
//        이름으로 검색한 결과와 jin 이랑 같으면 성공
        Users result = repository.findByName("안유진").get(0);
//        검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    @Test
    @DisplayName(".문제 1. 여성의 이름 중 \"w\"또는 \"m\"을 포함하는 자료를 검색하시오.")
    void 문제1() {
        List<Users> listW = repository.findByGenderAndNameContaining(Gender.Female, "w");
        List<Users> listM = repository.findByGenderAndNameContaining(Gender.Female, "m");
        List<Users> result = new ArrayList<>();
        result.addAll(listW);
        result.addAll(listM);
        result.forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 2. 이메일에 net을 포함하는 데이터 건수를 출력하시오.")
    void 문제2() {
        repository.findByEmailContains("net").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 3. 가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 \"J\"인 자료를 출력하시오.")
    void 문제3() {
        LocalDate baseDate = LocalDate.now().minusMonths(1L);
        LocalDateTime start = baseDate.atTime(0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        repository.findByNameStartingWithAndCreatedAtBetween("J", start, end)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("문제 4. 가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력하시오.")
    void 문제4() {

    }

    @Test
    @DisplayName("문제 5. \"Red\"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.\n" +
            "(예, apenley2@tripod.com  → apenley2)")
    void 문제5() {
        repository.findByGenderAndLikeColor(Gender.Male,"Red")
                .forEach(x->{
                    String email = x.getEmail();
                    String account = email.split("@")[0];
                    System.out.println(account);
                    });
    }
}

