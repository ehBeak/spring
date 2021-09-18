package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*@Column(name = "TEAM_ID") // 왜래키를 일대일로 DB와 맞춤
    private Long teamId;*/

    @ManyToOne // Member가 N인 곳에 ManyToOne
    @JoinColumn(name = "TEAM_ID") // name = "왜래키에 해당하는 컬럼명"
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void ChangeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 순수 객체 상태를 고려해서 항상 양쪽에 값 설정 => 연관관계 편의 메소드
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
