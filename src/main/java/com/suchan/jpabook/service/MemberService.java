package com.suchan.jpabook.service;

import com.suchan.jpabook.domain.Member;
import com.suchan.jpabook.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // JPA 는 트랜잭션 안에서 동작해야 함. 클래스 레벨에 Transactional 을 걸면 public 메소드에 알아서 트랜잭션 걸림
@RequiredArgsConstructor
public class MemberService {

    //Mock 객체를 통해서 메소드에 값을 세팅해줄 수 있기 때문에 필드 인젝션이 안 좋다?
    private final MemberRepository memberRepository;

//    public static void main(String[] args) {
//        MemberService memberService = new MemberService(); 처럼 테스트 케이스를 생성할 때 생성자에 파라미터를 넣지 않은 것을 미리 알 수 있음.
//    }

    //회원 가입
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //동시성 문제가 일어날 수도 있기 때문에 DB 에서 name 을 Unique 제약조건 걸어둬야 함.
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true) // read Only 를 true 로 하면 JPA 가 읽기 전용으로 최적화, DB에 따라서 DB 단에서 최적화 하기도 함.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true) // 쓰기에 넣으면 안 됨.
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    //회원 전체 조회

}
