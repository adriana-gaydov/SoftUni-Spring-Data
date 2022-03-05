package _06_FootballBettingDatabase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "country", targetEntity = Town.class)
    private Set<Town> towns;

    @ManyToOne(optional = false)
    @JoinTable(name = "countries_continents",
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Continent continent;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
        this.towns = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
