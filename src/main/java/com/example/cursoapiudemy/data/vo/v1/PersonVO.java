package com.example.cursoapiudemy.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"key", "firstName", "lastName", "gender", "address"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty(value = "id")
    private Long key;
    @JsonProperty(value = "first_name")
    private String firstName;

    private String lastName;
    private String address;
    private String gender;

    public PersonVO() {
    }

    public PersonVO(Long key, String firstName, String lastName, String address, String gender) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO person = (PersonVO) o;
        return Objects.equals(key, person.key) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstName, lastName, address, gender);
    }

//    @Override
//    public String toString() {
//        return "PersonVO{" +
//                "key=" + key +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", address='" + address + '\'' +
//                ", gender='" + gender + '\'' +
//                '}';
//    }
}