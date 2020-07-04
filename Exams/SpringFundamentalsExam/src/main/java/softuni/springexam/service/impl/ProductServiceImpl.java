package softuni.springexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexam.model.binding.ProductAddBindingModel;
import softuni.springexam.model.entity.Product;
import softuni.springexam.model.service.ProductServiceModel;
import softuni.springexam.model.view.ProductViewModel;
import softuni.springexam.repository.ProductRepository;
import softuni.springexam.service.CategoryService;
import softuni.springexam.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductServiceModel addProduct(ProductAddBindingModel productAddBindingModel) {
        ProductServiceModel productServiceModel = this.modelMapper
                .map(productAddBindingModel,ProductServiceModel.class);

        productServiceModel.setCategory(this.categoryService.getByCategory(productAddBindingModel.getCategory()));

        return this.modelMapper.map(
                this.productRepository.save(this.modelMapper.map(productServiceModel, Product.class)),
                ProductServiceModel.class
        );
    }

    @Override
    public List<ProductViewModel> getAll() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p,ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buy(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }
}
