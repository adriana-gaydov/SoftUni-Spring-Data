package com.example.productshopxml.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsRootDTO {

    @XmlElement(name = "user")
    private List<UserSoldProductsDTO> users;

    public UserSoldProductsRootDTO() {
    }

    public UserSoldProductsRootDTO(List<UserSoldProductsDTO> users) {
        this.users = users;
    }

    public List<UserSoldProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldProductsDTO> users) {
        this.users = users;
    }
}
