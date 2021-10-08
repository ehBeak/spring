package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    // * 일대일 -> 양방향, 읽기전용
    @OneToOne(mappedBy = "locker")
    private Member member;

    /* 대상 테이블에 외래 키 단방향 정리
    *  1. 단방향 관계는 jpa지원X
    *  2. 양방향 관계는 지원 */

    /* 내가 내 것만 관리!! */
}
