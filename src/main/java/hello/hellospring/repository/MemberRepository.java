package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  // 회원 저장
    Optional<Member> findById(Long id);  // ID
    Optional<Member> findByName(String name);  // 이름
    List<Member> findAll();  // 모든 회원 list
}
