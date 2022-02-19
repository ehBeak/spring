package hellojpa.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name = "my_sequence_value", sequenceName = "member_sequence", initialValue = 1, allocationSize = 50)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_value")
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
