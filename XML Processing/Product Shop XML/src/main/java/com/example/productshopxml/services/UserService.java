package com.example.productshopxml.services;

import com.example.productshopxml.entities.users.UserCountSoldProductsRoot;
import com.example.productshopxml.entities.users.UserSoldProductsRootDTO;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    UserSoldProductsRootDTO getAllUsersWithItemSold();

    @Transactional
    UserCountSoldProductsRoot getAllUsersWithProductsSoldWithCount();
}
