package com.example.products_shop.services.impl;

import com.example.products_shop.entities.products.Product;
import com.example.products_shop.entities.products.SoldProduct2DTO;
import com.example.products_shop.entities.products.SoldProduct2RootDTO;
import com.example.products_shop.entities.users.SellerFirstLastNameProductsSoldDTO;
import com.example.products_shop.entities.users.User;
import com.example.products_shop.entities.users.UserProductsDTO;
import com.example.products_shop.entities.users.UserProductsRootDTO;
import com.example.products_shop.repositories.UserRepository;
import com.example.products_shop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<SellerFirstLastNameProductsSoldDTO> getSellersFirstLastNameProductsSold() {
        List<User> sellers = userRepository.findSellers();

        return sellers.stream().map(e -> mapper.map(e, SellerFirstLastNameProductsSoldDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserProductsRootDTO getUsersAndProducts() {
        List<User> users = this.userRepository.findUsersByProductsGreaterThan();

        HashSet<UserProductsDTO> collect = users.stream().map(u -> {
            Set<SoldProduct2DTO> productsSold = u.getProductsSold().stream()
                    .map(p -> mapper.map(p, SoldProduct2DTO.class))
                    .collect(Collectors.toCollection(HashSet::new));

            SoldProduct2RootDTO soldProduct2RootDTO = new SoldProduct2RootDTO(productsSold);

            return new UserProductsDTO(u.getFirstName(), u.getLastName(), u.getAge(), soldProduct2RootDTO);
        }).collect(Collectors.toCollection(HashSet::new));

        return new UserProductsRootDTO(collect);
    }
}
