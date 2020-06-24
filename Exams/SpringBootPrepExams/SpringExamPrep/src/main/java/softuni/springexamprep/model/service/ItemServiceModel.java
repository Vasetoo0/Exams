package softuni.springexamprep.model.service;

import softuni.springexamprep.model.entity.CategoryName;

import java.math.BigDecimal;

public class ItemServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private BigDecimal price;
    private CategoryServiceModel category;
    private String gender;
    private String image;

    public ItemServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }

    public String getImage() {
        return String.format("/img/%s-%s.jpg", this.gender,
                this.category.getName().name());
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
