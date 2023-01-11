package com.secure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String state;
    private String city;
    private String country;
    private String zip;



    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null
                ? firstName.hashCode() : 0); result = 31 * result + (lastName != null
                ? lastName.hashCode() : 0); result = 31 * result + (street != null
                ? street.hashCode() : 0); result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0); result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (country != null
                ? country.hashCode() : 0);
        return result; }

    public void setLastViewed(String toString) {

    }
}
