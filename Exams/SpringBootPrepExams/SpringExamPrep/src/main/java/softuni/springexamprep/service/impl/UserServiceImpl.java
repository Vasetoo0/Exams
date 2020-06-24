package softuni.springexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.springexamprep.model.entity.User;
import softuni.springexamprep.model.service.UserServiceModel;
import softuni.springexamprep.repository.UserRepository;
import softuni.springexamprep.service.UserService;

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
    public UserServiceModel registerUser(UserServiceModel user) {

        User userReg = this.modelMapper.map(user,User.class);

        return this.modelMapper.map(this.userRepository.save(userReg),UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsername(String username) {

        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u,UserServiceModel.class))
                .orElse(null);

    }
}
