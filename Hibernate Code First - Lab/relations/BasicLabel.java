package relations;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String name;

    @OneToOne(mappedBy = "label",
    targetEntity = BasicShampoo.class)
    private BasicShampoo basicShampoo;

    public BasicLabel() {
    }

    public BasicLabel(String name) {
        this.name = name;
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

    public BasicShampoo getBasicShampoo() {
        return basicShampoo;
    }

    public void setBasicShampoo(BasicShampoo basicShampoo) {
        this.basicShampoo = basicShampoo;
    }
}
