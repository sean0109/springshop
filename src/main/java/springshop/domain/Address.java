package springshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    @Builder
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
