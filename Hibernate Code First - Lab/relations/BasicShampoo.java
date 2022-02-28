package relations;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(optional = false)
    @JoinColumn(name = "label_id",
    referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Ingredient> ingredients;

    @ManyToOne()
    @JoinColumn(name = "batch_id",
    referencedColumnName = "id")
    private ProductionBatch productionBatch;

    public BasicShampoo() {
    }

    public ProductionBatch getProductionBatch() {
        return productionBatch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasicLabel getLabel() {
        return label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    public void setProductionBatch(ProductionBatch productionBatch) {
        this.productionBatch = productionBatch;
    }
}
