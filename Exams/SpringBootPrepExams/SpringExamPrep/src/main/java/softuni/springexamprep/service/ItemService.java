package softuni.springexamprep.service;

import softuni.springexamprep.model.service.ItemServiceModel;

import java.util.List;

public interface ItemService {

    ItemServiceModel addItem(ItemServiceModel itemServiceModel);

    List<ItemServiceModel> getAll();

    ItemServiceModel getById(String id);

    void deleteItem(String id);
}
