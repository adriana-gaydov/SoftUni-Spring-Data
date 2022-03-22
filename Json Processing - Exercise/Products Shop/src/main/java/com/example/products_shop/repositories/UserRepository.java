package com.example.products_shop.repositories;

import com.example.products_shop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u" +
            " JOIN u.productsSold p" +
            " WHERE p.buyer IS NOT NULL" +
            " ORDER BY size(u.productsSold) DESC, u.lastName ASC")
    List<User> findSellers();

    @Query("SELECT u FROM User u JOIN u.productsSold p WHERE size(p) > 0" +
            " ORDER BY size(p) DESC, u.lastName ASC")
    List<User> findUsersByProductsGreaterThan();
}
