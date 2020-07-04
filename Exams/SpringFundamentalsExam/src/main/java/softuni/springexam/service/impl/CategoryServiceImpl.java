package softuni.springexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexam.model.entity.Category;
import softuni.springexam.model.entity.CategoryName;
import softuni.springexam.model.service.CategoryServiceModel;
import softuni.springexam.repository.CategoryRepository;
import softuni.springexam.service.CategoryService;

import javax.annotation.PostConstruct;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        if(this.categoryRepository.count() == 0) {
            for (CategoryName value : CategoryName.values()) {
                this.categoryRepository.save(new Category(value,"Yammyyy"));
            }
        }
    }

    @Override
    public CategoryServiceModel getByCategory(CategoryName name) {

        return this.modelMapper.map(this.categoryRepository.findCategoryByName(name),CategoryServiceModel.class);
    }
}
