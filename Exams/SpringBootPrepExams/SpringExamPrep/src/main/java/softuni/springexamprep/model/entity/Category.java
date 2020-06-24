package softuni.springexamprep.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryName name;
    private String description;

    public Category() {
    }

    public Category(CategoryName name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    @Enumerated
    @Column(nullable = false,unique = true)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
