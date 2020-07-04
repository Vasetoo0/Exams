package softuni.springexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexam.model.binding.UserRegisterBindingModel;
import softuni.springexam.model.entity.User;
import softuni.springexam.model.service.UserServiceModel;
import softuni.springexam.repository.UserRepository;
import softuni.springexam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        return this.modelMapper.map(
                this.userRepository.save(this.modelMapper.map(userServiceModel, User.class)),
                UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u,UserServiceModel.class))
                .orElse(null);
    }
}
