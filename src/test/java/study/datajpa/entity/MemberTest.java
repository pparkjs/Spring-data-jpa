package study.datajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;
    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member membe1 = new Member("membe1", 10, teamA);
        Member membe2 = new Member("membe2", 20, teamA);
        Member membe3 = new Member("membe3", 30, teamB);
        Member membe4 = new Member("membe4", 40, teamB);

        em.persist(membe1);
        em.persist(membe2);
        em.persist(membe3);
        em.persist(membe4);

        // 초기화
        em.flush(); // 강제로 DB에 insert 쿼리 날림
        em.clear(); // DB 쿼리 날리고 JPA 영속성 컨텍스트의 캐시를 다 날림

        //확인
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.team = " + member.getTeam());
        }

    }

    @Test
    public void jpaEventBaseEntity() throws Exception {
        //given
        Member member = new Member("member1");
        memberRepository.save(member); //@PrePersist

        Thread.sleep(100); //테스트 코드에서는 이런거 안좋음
        member.setUsername("member2");

        em.flush(); // @PreUpdate
        em.clear();

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        System.out.println("findMember.createdDate = " + findMember.getCreatedDate());
        System.out.println("findMember.updatedDate = " + findMember.getLastModifiedDate());
        System.out.println("findMember.updatedDate = " + findMember.getCreatedBy());
        System.out.println("findMember.updatedDate = " + findMember.getLastModifiedBy());

        //when

        //then
    }
}
