package softuni.springexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexamprep.model.entity.Item;
import softuni.springexamprep.model.service.ItemServiceModel;
import softuni.springexamprep.repository.ItemRepository;
import softuni.springexamprep.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemServiceModel addItem(ItemServiceModel itemServiceModel) {

        Item item = this.modelMapper.map(itemServiceModel,Item.class);

        return this.modelMapper.map(this.itemRepository.save(item),ItemServiceModel.class);
    }

    @Override
    public List<ItemServiceModel> getAll() {
        return this.itemRepository.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i,ItemServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemServiceModel getById(String id) {
        return this.itemRepository.findById(id)
                .map(i -> this.modelMapper.map(i,ItemServiceModel.class))
                .orElse(null);
    }

    @Override
    public void deleteItem(String id) {
        this.itemRepository.deleteById(id);
    }
}
