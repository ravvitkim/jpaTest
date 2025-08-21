package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//테스트 코드 작성 단축기 : ctrl shift T
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//BaseEntity의 ToString 호출
@ToString(callSuper = true)
public class UserTest extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

}
