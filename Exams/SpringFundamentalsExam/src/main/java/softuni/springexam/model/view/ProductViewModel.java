package softuni.springexam.model.view;

import softuni.springexam.model.entity.CategoryName;

public class ProductViewModel {

    private String id;
    private String name;
    private String price;
    private CategoryViewModel category;
    private String imgPath;

    public ProductViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CategoryViewModel getCategory() {
        return category;
    }

    public void setCategory(CategoryViewModel category) {
        this.category = category;
    }

    public String getImgPath() {
        return String.format("/img/%s.png",this.category.getName().name().toLowerCase());
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}