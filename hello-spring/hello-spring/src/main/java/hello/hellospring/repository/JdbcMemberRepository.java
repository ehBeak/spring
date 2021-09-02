package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepositroy{

    /* DB서버에 연결되면 주는 dataSource를 받기 위한 변수
    *  properties에서 DB명시 -> 스프링부트에서 dataSource 만들어 놓음
    *  -> 만들어 놓은 dataSource로 서버에서 보낸 dataSource받음
    *  : 생성자 주입*/
    private final DataSource dataSource;

    /* 생성자 주입 */
    public JdbcMemberRepository(DataSource dataSource) {
        // dataSource에 getConnection()존재 : DB와 연결
        this.dataSource = dataSource;
    }

    /* 회원 정보 저장 */
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());

            pstmt.executeUpdate(); // 쿼리 실행하고 업데이트해라
            rs = pstmt.getGeneratedKeys();

            if(rs.next()) {
                member.setId(rs.getLong(1));
            }else {
                throw new SQLException("id 조회 실패");
            }

            return member;
        } catch (Exception e) {
            throw  new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* ID로 회원 조회 */
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery();

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

    /* 이름으로 회원 조회 */
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);

            rs = pstmt.executeQuery();

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

    /* 모든 회원 조회 */
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<Member> members = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            members = new ArrayList<Member>();

            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;

        } catch (Exception e) {
            throw new IllegalStateException();
        } finally {
            close(conn, pstmt, rs);
        }

    }

    /* Connection */
    public Connection getConnection() {
        // spring framework에서는 DataSourceUtils를 통해서 getConnection을 해야함
        return DataSourceUtils.getConnection(dataSource);
    }

    /* 자원 정리 */
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        // 역순으로 자원정리
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null) {
                close(conn);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Connection 자원정리 */
    private void close(Connection conn) throws SQLException {
        // spring framework에서는 Connection을 DataSourceUtils를 통해서 자원정리를 해야한다.
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
