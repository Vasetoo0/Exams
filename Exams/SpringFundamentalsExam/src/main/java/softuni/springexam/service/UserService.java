package softuni.springexam.service;

import softuni.springexam.model.binding.UserRegisterBindingModel;
import softuni.springexam.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByUsername(String username);
}
