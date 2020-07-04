package softuni.springexam.service;

import softuni.springexam.model.binding.ProductAddBindingModel;
import softuni.springexam.model.service.ProductServiceModel;
import softuni.springexam.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductAddBindingModel productAddBindingModel);

    List<ProductViewModel> getAll();

    void buy(String id);

    void buyAll();
}
