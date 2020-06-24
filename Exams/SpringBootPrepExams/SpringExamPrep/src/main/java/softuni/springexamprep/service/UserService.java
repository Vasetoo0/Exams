package softuni.springexamprep.service;

import softuni.springexamprep.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel user);

    UserServiceModel getByUsername(String username);
}
