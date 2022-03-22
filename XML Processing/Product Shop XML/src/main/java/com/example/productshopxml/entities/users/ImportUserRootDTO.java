package com.example.productshopxml.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportUserRootDTO {

    @XmlElement(name = "user")
    private Set<ImportUserDTO> users;

    public Set<ImportUserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<ImportUserDTO> users) {
        this.users = users;
    }
}
