package hellojpa;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String zipcode;
    private String street;

    // Address라는 값타입은 Member라는 Entity를 가질 수 있다.
    //private Member member;

    public Address() {
    }

    // Address를 불변객체로 만들기 위해 생성자에 값을 설정하고 set함수는 지운다.
    // 불변 객체를 사용하지 않으면 entity끼리 값을 공유할 수도 있는 문재 생김
    public Address(String city, String zipcode, String street) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    /*public void setCity(String city) {
        this.city = city;
    }*/

    public String getZipcode() {
        return zipcode;
    }

    /*public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }*/

    public String getStreet() {
        return street;
    }

    /*public void setStreet(String street) {
        this.street = street;
    }*/

    // 동등성의 비교
    // equals() overide
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(zipcode, address.zipcode) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, zipcode, street);
    }
}
