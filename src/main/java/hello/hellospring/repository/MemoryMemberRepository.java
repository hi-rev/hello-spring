package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;
// TDD(테스트 주도 개발)
// => 테스트를 먼저 만들고 테스트를 통과하기 위한 것을 짜는 것
// => 만드는 과정에서 우선 테스트를 작성하고 그걸 통과하는 코드를 만들고를 반복하면서 제대로 동작하는지에 대한 피드백

// implements 를 통해 interface 사용
// Alt + Enter 단축키를 통해 interface 메소드 모두 가져오기 가능
public class MemoryMemberRepository implements MemberRepository {

    // HashMap에 <key, value> 를 <Long, Member> 타입으로 저장
    // (회원ID, 값)
    // 데이터를 저장하기 위한 store 이라는 데이터 저장 HashMap 생성
    private static Map<Long, Member> store = new HashMap<>();
    // key 값을 생성해주는 역할
    private static long sequence = 0L;

    // 오버라이드를 통해 interface에 있는 메소드 가져옴
    // 기능 구현
    @Override
    public Member save(Member member) {
        // sequence: id값, 멤버를 저장할 때마다 순차적으로 값을 증가하면서 저장
        member.setId(++sequence);
        store.put(member.getId(), member); // HashMap 삽입
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Optional: NPE 방지
        // store 라는 저장 객체에서 id 값을 얻음
        // key 값으로 찾기
        return Optional.ofNullable(store.get(id));
    }

    // name 데이터 찾기
    // getName()이 파라미터로 넘어온 name과 같은지 확인한다.
    // findFirst(): filter 조건에 일치하는 element 1개를 Optional로 리턴
    // findAny(): findFirst()와 동일한 역할을 한다.
    // 하지만 차이점은 findFirst()는 여러 요소가 조건에 부합해도 가장 Stream의 순서에서 앞에 있는 요소를 리턴
    // 반면 findAny()는 순서와 관계없이 가장 먼저 찾은 요소를 리턴
    // 만일 찾은 값이 없다면 null 값 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // store에 있는 모든 값 찾기
    // store에 있는 value들(member) 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // Test 할 때마다 store를 비워주기 위함
    public void clearStore() {
        store.clear();
    }
}
