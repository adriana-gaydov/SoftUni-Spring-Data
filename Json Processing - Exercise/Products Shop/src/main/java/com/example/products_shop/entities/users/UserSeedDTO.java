package com.example.products_shop.entities.users;

import com.example.products_shop.exceptions.NameNotInBoundsException;

public class UserSeedDTO {

    private String firstName;
    private String lastName;
    private Integer age;

    public UserSeedDTO(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        validate();
    }

    private void validate() {
        if (this.lastName.length() < 3) {
            throw new NameNotInBoundsException();
        }
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
