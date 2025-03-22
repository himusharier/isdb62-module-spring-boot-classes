package org.himusharier.validationexample.service;

import org.himusharier.validationexample.dto.UserRequest;
import org.himusharier.validationexample.entity.User;
import org.himusharier.validationexample.exception.UserNotFoundException;
import org.himusharier.validationexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(UserRequest userRequest) {
        User user = User.build(
                0,
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getMobile(),
                userRequest.getGender(),
                userRequest.getAge(),
                userRequest.getNationality());
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(int id) {
        User user = repository.findByUserId(id); //"findBy" followed by the variable name "userId"
        if (user == null) {
            throw new UserNotFoundException("user not found with the id: " + id);
        }
        return user;
    }
}
