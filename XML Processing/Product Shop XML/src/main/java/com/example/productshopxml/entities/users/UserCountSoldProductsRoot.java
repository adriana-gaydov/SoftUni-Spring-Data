package com.example.productshopxml.entities.users;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCountSoldProductsRoot {

    @XmlAttribute(name = "count")
    private long userCount;

    @XmlElement(name = "user")
    private List<UserCountSoldProductsDTO> users;

    public UserCountSoldProductsRoot() {
    }

    public UserCountSoldProductsRoot(List<UserCountSoldProductsDTO> users) {
        this.users = users;
        this.userCount = users.size();
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public List<UserCountSoldProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserCountSoldProductsDTO> users) {
        this.users = users;
    }
}
