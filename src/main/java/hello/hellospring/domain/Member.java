package hello.hellospring.domain;

import javax.persistence.*;

// 회원ID와 이름 필요
// 회원 도메인 Member 클래스
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 고객이 정하는 id가 아니라 시스템이 정하는 id
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
