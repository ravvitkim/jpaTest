package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GirlGroup {
    @Id
    private String g_id;
    private String g_name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "e_id")
    Entertainment entertainment;

    @Builder.Default
    @OneToMany(mappedBy = "girlGroup",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    List<IdolMember> members = new ArrayList<>();
}
