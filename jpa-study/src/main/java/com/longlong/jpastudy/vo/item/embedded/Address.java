package com.longlong.jpastudy.vo.item.embedded;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
<<<<<<< Updated upstream
import java.io.Serializable;
=======
>>>>>>> Stashed changes
import java.util.Objects;

/**
 * Created by Kim Young Long.
 * Date : 2021-04-27.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream
public class Address implements Serializable {
=======
@EqualsAndHashCode
public class Address {
>>>>>>> Stashed changes
    private String city;
    private String street;
    private String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}