package softuni.springexamprep.service;

import softuni.springexamprep.model.entity.CategoryName;
import softuni.springexamprep.model.service.CategoryServiceModel;

public interface CategoryService {

    CategoryServiceModel getByCategory(CategoryName categoryName);
}
