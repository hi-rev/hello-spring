package hello.hellospring.repository;
// repository 패키지 -> 회원 정보를 저장하는 저장소
import hello.hellospring.domain.Member; // Member.java
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원을 저장하면 저장된 회원이 반환
    // 1. save()를 통해 회원 저장
    Member save(Member member);  // 회원 저장
    // 2. ID로 저장된 회원 찾을 수 있음
    // Optional 이란? findById로 가져올 때 만일 null 값일 때 처리하는 방법 중 하나
    // => NPE 에러 방지용
    // <>은 제네릭(Generics)이라고 하며, 객체의 타입을 지정해준다.
    // 제네릭의 장점
    // 1. 타입의 안정성, 불필요한 형변환을 줄여 코드의 간결함
    Optional<Member> findById(Long id);  // ID
    // 3. 이름으로 저장된 회원 찾을 수 있음
    Optional<Member> findByName(String name);  // 이름
    // 4. 저장된 모든 회원 리스트를 불러올 수 있음
    List<Member> findAll();  // 모든 회원 list
}
