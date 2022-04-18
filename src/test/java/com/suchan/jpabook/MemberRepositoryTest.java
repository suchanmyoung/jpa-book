package com.suchan.jpabook;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // JPA는 트랜잭션 위에서 동작함, Transactional 어노테이션은 테스트 케이스에 있으면 테스트가 끝난 후 롤백이 됨
    @Rollback(value = false) // 롤백 하기 싫으면 추가
    public void testMember() throws Exception {

        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // findMember == member 영속성 컨텍스트에서 1차 캐시에서 같은 걸로 나옴... 어려움..
    }
}