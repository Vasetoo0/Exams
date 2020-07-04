package softuni.springexam.model.view;

import softuni.springexam.model.entity.CategoryName;

public class CategoryViewModel {

    private CategoryName name;
    private String description;

    public CategoryViewModel() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
