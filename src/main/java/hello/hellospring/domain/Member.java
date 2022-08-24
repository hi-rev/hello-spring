package hello.hellospring.domain;
import javax.persistence.*;

// 회원ID와 이름 필요
// 회원 도메인 Member 클래스
@Entity
public class Member {
    // GeneratedValue 어노테이션은 기본키 생성을 데이터베이스에게 위임하는 방식이다.
    // 우리는 이 프로젝트에서 이름만 입력하면 자동으로 id값을 생성해주고자 하고 있다.
    // 이를 Identity 전략이라고 하는데,
    // id 값을 따로 할당하지 않아도 데이터베이스가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성해준다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 고객이 정하는 id가 아니라 시스템이 정하는 id

//    @Column 어노테이션을 사용하여 name 항목의 컬럼명을 username으로 설정할 수 있다.
//    @Column(name = "username")
    private String name;

    // id와 name이 private이기 때문에 getter setter 생성
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
