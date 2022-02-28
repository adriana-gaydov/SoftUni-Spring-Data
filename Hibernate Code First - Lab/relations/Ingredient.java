package relations;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String name;

    @ManyToMany(mappedBy = "ingredients",
    targetEntity = BasicShampoo.class,
    fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BasicShampoo> shampoos;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }


}
