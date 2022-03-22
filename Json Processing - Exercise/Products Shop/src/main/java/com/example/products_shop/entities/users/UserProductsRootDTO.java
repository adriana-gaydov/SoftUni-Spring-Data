package com.example.products_shop.entities.users;

import java.util.Set;

public class UserProductsRootDTO {

    private long usersCount;
    private Set<UserProductsDTO> users;

    public UserProductsRootDTO(Set<UserProductsDTO> users) {
        this.usersCount = users.size();
        this.users = users;
    }

    public long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(long usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserProductsDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserProductsDTO> users) {
        this.users = users;
    }
}
