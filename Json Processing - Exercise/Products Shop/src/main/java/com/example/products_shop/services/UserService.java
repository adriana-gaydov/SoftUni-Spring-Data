package com.example.products_shop.services;

import com.example.products_shop.entities.users.SellerFirstLastNameProductsSoldDTO;
import com.example.products_shop.entities.users.UserProductsRootDTO;

import java.util.List;

public interface UserService {

    List<SellerFirstLastNameProductsSoldDTO> getSellersFirstLastNameProductsSold();

    UserProductsRootDTO getUsersAndProducts();
}
