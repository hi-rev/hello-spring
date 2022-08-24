package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// JPA는 모든 변경이 트랜잭션 안에서 실행되어야 함
// 트랜잭션이란, 데이터베이스의 상태를 변화시키기 위해서 수행하는 작업의 단위를 뜻함
@Transactional
public class JpaMemberRepository implements MemberRepository {

    // JPA는 엔티티 매니저라는 것으로 모두 동작을 한다.
    // 스프링부트가 자동으로 엔티티 매니저를 만들어준다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // insert문을 쓰지 않아도 자동으로 다 데이터를 생성하여 DB에 넣어준다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // jpql 작성
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql 사용
        // 보통은 테이블을 대상으로 쿼리를 날리는데,
        // jpql은 객체를 대상으로 쿼리를 날린다.
        // Member 엔티티를 조회(m은 Member as m을 의미한다.)
        // createQuery 메서드를 호출할 때 두 번째 인자로 엔티티 클래스를 넘겨준다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
