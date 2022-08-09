package hello.hellospring.domain;
// 회원ID와 이름 필요
public class Member {
    private Long id;
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
