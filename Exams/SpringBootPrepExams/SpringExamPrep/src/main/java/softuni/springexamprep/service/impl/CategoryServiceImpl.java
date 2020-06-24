package softuni.springexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexamprep.model.entity.Category;
import softuni.springexamprep.model.entity.CategoryName;
import softuni.springexamprep.model.service.CategoryServiceModel;
import softuni.springexamprep.repository.CategoryRepository;
import softuni.springexamprep.service.CategoryService;

import javax.annotation.PostConstruct;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init(){
        if(this.categoryRepository.count() == 0) {
            for (CategoryName value : CategoryName.values()) {
                this.categoryRepository.save(new Category(value,"Nice"));
            }
        }
    }

    @Override
    public CategoryServiceModel getByCategory(CategoryName categoryName) {

        return this.modelMapper.map(this.categoryRepository.findByName(categoryName),CategoryServiceModel.class);
    }
}
