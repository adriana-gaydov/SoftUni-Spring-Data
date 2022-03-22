package com.example.productshopxml.services.impl;

import com.example.productshopxml.entities.products.*;
import com.example.productshopxml.entities.users.*;
import com.example.productshopxml.repositories.UserRepository;
import com.example.productshopxml.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserSoldProductsRootDTO getAllUsersWithItemSold() {
        List<User> users = this.userRepository.findByItemSold();

        List<UserSoldProductsDTO> soldProductsDTOS = users.stream()
                .map(u -> {
                    Set<Product> productsSold = u.getProductsSold();

                    Set<ProductNamePriceBuyerNamesDTO> collect = productsSold.stream()
                            .map(e -> mapper.map(e, ProductNamePriceBuyerNamesDTO.class))
                            .collect(Collectors.toSet());

                    ProductNamePriceBuyerNamesRootDTO dto = new ProductNamePriceBuyerNamesRootDTO(collect);

                    return new UserSoldProductsDTO(u.getFirstName(), u.getLastName(), dto);
                }).toList();

        return new UserSoldProductsRootDTO(soldProductsDTOS);
    }

    @Override
    public UserCountSoldProductsRoot getAllUsersWithProductsSoldWithCount() {
        List<User> users = this.userRepository.findByHavingProductSoldOrderByProductsSoldThenByLastName();

        List<UserCountSoldProductsDTO> usersDTO = users.stream()
                .map(u -> {
                    List<ProductCountNamePriceDTO> productsDTO = u.getProductsSold().stream()
                            .map(p -> mapper.map(p, ProductCountNamePriceDTO.class)).toList();

                    ProductCountNamePriceRootDTO productsRootDTO = new ProductCountNamePriceRootDTO(productsDTO);

                    return new UserCountSoldProductsDTO(u.getFirstName(), u.getLastName(), u.getAge(),
                            productsRootDTO);
                }).toList();

        return new UserCountSoldProductsRoot(usersDTO);
    }
}
