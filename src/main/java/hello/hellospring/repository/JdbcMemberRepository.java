package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
    // 먼저, DB를 사용하려면 데이터 소스라는 것이 필요하다.
    // 스프링이 dataSource라는 것을 만들어놓는다. 이것은 주입받을 수 있도록 해준다.
    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    // save 하려면 쿼리 짜야함
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)"; // sql문
        Connection conn = null; // 데이터베이스 커넥션
        PreparedStatement pstmt = null; // SQL DB에 전달
        ResultSet rs = null; // 결과를 받는 것

        try {
            conn = getConnection();
            // prepareStatement를 통해 sql을 넣음
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // parameterIndex는 위 sql문 (?)과 매칭된다.
            pstmt.setString(1, member.getName());

            pstmt.executeUpdate(); // 실제 쿼리 전송
            rs = pstmt.getGeneratedKeys(); // DB에서 데이터를 꺼내줌

            if (rs.next()) { // 값이 있으면 꺼냄
                member.setId(rs.getLong(1));
            } else { // 없으면 실패 문구
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 한명 찾기
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            // 값이 존재하면 멤버 객체를 만든다음에 반환을 해준다.
            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 멤버 모두 찾기
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();

            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        // getConnection을 쓸 때는 DataSourceUtils를 통해 가지고 와야함
        // getConnection: 실제 자바 프로그램과 데이터베이스를 네트워크 상에서 연결해주는 메소드
        // 연결에 성공하면 DB와 연결된 상태를 Connection 객체로 표현하여 반환
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
