package softuni.springexam.service;


import softuni.springexam.model.entity.CategoryName;
import softuni.springexam.model.service.CategoryServiceModel;

public interface CategoryService {

    CategoryServiceModel getByCategory(CategoryName name);
}
