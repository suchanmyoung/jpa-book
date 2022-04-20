package com.suchan.jpabook.respository;

import com.suchan.jpabook.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     * 스프링이 엔티티매니저를 만들어서 주입해줌
     * 싱글톤이라 동시성 문제가 될 수 있지만, 스프링은 여기에 실제 EntityManager 를 주입하는 것이 아니라, 사실은 실제 EntityManager 를 연결해주는 가짜를 만든다.
     * 그리고 가짜를 호출하면 현재 DB 트랜잭션과 관련된 실제를 호출한다.
     */
    private final EntityManager em; // 이렇게 사용하는 건 스프링 부트만 됨.

    /**
     * 만약 EntityManagerFactory 를 관리하고 싶으면 @PersistenceUnit 을 사용한다
     *  @PersistenceUnit
     *  private EntityManagerFactory emf;
     */

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // Member 엔티티에 대한 조회
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
