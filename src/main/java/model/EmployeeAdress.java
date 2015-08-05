package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Dawid on 2015-07-16.
 */
@Embeddable
public class EmployeeAdress {
    @Column(name="country")
    private String country;
    @Column(name="city")
    private String city;
    @Column(name="postal_code")
    private String postalCode;

    public EmployeeAdress() {
    }

    public EmployeeAdress(String country, String city, String postalCode) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
