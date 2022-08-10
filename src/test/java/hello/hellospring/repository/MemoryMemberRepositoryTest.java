package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    // 테스트를 위하여 MemoryMemberRepository 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나 돌릴 때마다 초기화를 위하여 clearStore() 사용
    // 보통 Test 메소드가 사용되고 난 후 종료되어야 할 리소스를 처리할 때 사용
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // Test 어노테이션은 테스트를 만드는 모듈 역할을 한다.
    // @Test가 붙어있는 메소드는 main 메소드처럼 IDE로 직접 실행할 수 있는 메소드
    @Test
    public void save() {
        Member member = new Member(); // 멤버 객체 생성
        member.setName("spring"); // 이름에 spring 입력

        repository.save(member); // 멤버 저장 -> save() 메소드는 sequence 값을 증가 시켜 ID로 들어갔고, 이름은 입력된 spring이 들어감
        // Optional에서 값을 꺼낼 때 get()을 통하여 바로 꺼낼 수 있음
        Member result = repository.findById(member.getId()).get(); // 여기서 입력된 ID를 받아오는 변수 result 생성
        assertThat(member).isEqualTo(result); // 이름이 입력된 멤버 객체와 저장한 repository 객체 안에 있는 result 가 일치하는지 확인
        // => 테스트 성공적: 데이터를 잘 저장하고 잘 받아오고 있음
    }

    @Test
    public void findByName() {
        // 객체 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);  // ID와 멤버 저장

        // 객체 생성
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);  // ID와 멤버 저장

        Member result = repository.findByName("spring1").get();  // "spring1"을 찾아서 가져옴

        // JUnit을 활요한 테스트
        // assertThat 구문을 활용
        // => 첫 번째 파라미터는 비교 대상의 실제값을, 두 번째 파라미터로는 비교로직이 담긴 Matcher 사용
        // 두 값이 일치하는지 확인
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        // 객체 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 객체 생성
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        // 저장되어 있는 데이터들 확인
        List<Member> result = repository.findAll();
        // 저장된 데이터의 수가 2가 맞는지 확인
        assertThat(result.size()).isEqualTo(2);
    }
}
