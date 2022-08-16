package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// 서비스는 비즈니스를 처리하는 용도이기 때문에 메서드명 같은 경우에도 비즈니스와 관련된 용어를 사용하는 것이 좋다.

// Test 파일 만들 때 단축키: 클래스 안에서 Ctrl + Shift + T
@Service
public class MemberService {
    // MemberRepository 인터페이스를 참조하는 새 MemoryMemberRepository 객체...?
    private final MemberRepository memberRepository;

    // new로 객체를 생성하지 않고
    // @Autowired를 통한 객체 생성
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    // 만일 같은 이름의 회원 가입을 불가능하다고 가정하였을 때(중복 member X)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        // result.get() : 그냥 단순하게 값을 꺼내고 싶다면 get() 사용 but 권장하지는 않음
        memberRepository.save(member); // 멤버 이름 저장 (ID는 자동 생성)
        return member.getId();
    }

    // 전체 회원 조회 메소드
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회 메소드
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 단축키를 이용해 메소드 만들기: Ctrl + Alt + Shift
    // 중복 회원 검증 메소드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent(): 만일 값이 있다면 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
