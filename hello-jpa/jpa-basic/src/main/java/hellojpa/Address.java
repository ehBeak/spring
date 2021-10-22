package hellojpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String zipcode;
    private String street;

    // Address라는 값타입은 Member라는 Entity를 가질 수 있다.
    private Member member;

    public Address() {
    }

    public Address(String city, String zipcode, String street) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
