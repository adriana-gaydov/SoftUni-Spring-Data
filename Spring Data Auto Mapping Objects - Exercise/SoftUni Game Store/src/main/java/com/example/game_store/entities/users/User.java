package com.example.game_store.entities.users;

import com.example.game_store.entities.games.Game;
import com.example.game_store.entities.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;

    @ManyToMany(mappedBy = "buyer", targetEntity = Order.class)
    private Set<Order> orders;

    @Column(nullable = false)
    private boolean admin;

    @Transient
    private Set<Game> shoppingCart;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
        this.shoppingCart = new HashSet<>();
    }

    public User(String email, String password, String fullName) {
        this();

        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void addToShoppingCart(Game game) {
        this.shoppingCart.add(game);
    }

    public void removeFromShoppingCart(Game game) {
        this.shoppingCart.remove(game);
    }

    public void clearShoppingCart() {
        this.shoppingCart.clear();
    }

    public BigDecimal getShoppingCartPrice() {
        return this.shoppingCart.stream().map(Game::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && admin == user.admin && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(fullName, user.fullName) && Objects.equals(games, user.games) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, fullName, games, orders, admin);
    }

    public Set<Game> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<Game> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
