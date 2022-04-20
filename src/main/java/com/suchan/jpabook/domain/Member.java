package com.suchan.jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//실무에서는 Getter는 열고 Setter는 꼭 필요한 경우에 사용
@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Order 필드에 있는 member 테이블에 매핑되어 있는 거
    private List<Order> orders = new ArrayList<>();

}
