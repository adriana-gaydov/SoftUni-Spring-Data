package com.example.productshopxml.repositories;

import com.example.productshopxml.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.productsSold p" +
            " WHERE p.buyer IS NOT NULL")
    List<User> findByItemSold();

    @Query("SELECT u FROM User u JOIN u.productsSold p" +
            " WHERE p.buyer IS NOT NULL" +
            " GROUP BY u" +
            " ORDER BY COUNT(p) DESC, u.lastName")
    List<User> findByHavingProductSoldOrderByProductsSoldThenByLastName();
}
