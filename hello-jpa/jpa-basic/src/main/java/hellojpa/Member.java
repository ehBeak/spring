package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member extends BaseEntity { //

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER) // 연결된 테이블을 한번에 같이 조회
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    // * 일대일 단방향
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    /*@ManyToMany
    @JoinColumn(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();*/

    // 다대다 -> 일대다 + 일대다
    @OneToMany(mappedBy = "Member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


    /*
    // 기간 Period
    private LocalDateTime startDate;
    private LocalDateTime endDate;*/

    @Embedded // embeded 타입
    private Period period;

    // 주소
    /*private String city;
    private String zipcode;
    private String street;
    */

    @Embedded // embeded 타입
    private Address homeAddress;

    // 중복 일어날 때는 AttributeOverrides
    // {}안에 @AttributeOverride(name = "기존 변수명", column = @Column(name = "중복을 피하기 위한 변수명=> 테이블에 반영되는.."))
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_EMP")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_END"))
    })
    private Address workAddress;

    /* 값 타입 컬렉션 :  값 타입을 하나 이상 저장할 때 */

    // 1. String과 같이 기본 값 타입일 때
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "MEMBER_ID") // address 테이블에서 fk에 해당, 연결될 컬럼
    )
    @Column(name = "FOOD_NAME") // 자바에서 제공하는 타입이기 때문에 예외적으로 컬럼이름 지정
    private Set<String> favoriteFoods = new HashSet<>();

    // 2. 임베디드 타입일 때
    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address> addressHistory = new ArrayList<>();

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }
}
