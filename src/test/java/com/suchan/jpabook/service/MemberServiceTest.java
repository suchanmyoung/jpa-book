package com.suchan.jpabook.service;

import com.suchan.jpabook.domain.Member;
import com.suchan.jpabook.respository.MemberRepository;
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
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Autowired
    MemberRepository memberRepository;

//    @Autowired
//    EntityManager em; DB에 나가는 쿼리를 보고 싶을 때는 Entity Manager 를 테스트코드에서 직접 불러서 em.flush 로 쿼리를 날려주면 됨

    @Test
    @Rollback(value = false) // Test 내부에서 Transactional은 자동적으로 Rollback 하는데 그걸 끄는 거
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
    

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다");
    }

}