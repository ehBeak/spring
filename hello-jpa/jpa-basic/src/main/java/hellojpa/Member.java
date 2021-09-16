package hellojpa;

import javax.persistence.*;
import java.util.Date;

//@Table(name="USERNAME")
@Entity
public class Member {

    @Id // pk
    private Long id;

    @Column(name = "name") // username ~ name
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // 객체의 enum타입 => ORDINAL타입 사용 X
    private RoleType roleType;
    //=> 중간에 enum타입을 추가하면 순서로 하기 때문에 값의 중복이 일어남
    @Temporal(TemporalType.TIMESTAMP)// 날짜 타입
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }
}
