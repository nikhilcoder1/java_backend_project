package com.project.full_stack_backend.controller;

import com.project.full_stack_backend.exception.UserNotFoundException;
import com.project.full_stack_backend.model.User;
import com.project.full_stack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://java-backend-project.vercel.app/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newuser , @PathVariable Long id ){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newuser.getUsername());
                    user.setEmail((newuser.getEmail()));
                    user.setName(newuser.getName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new  UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        return "User with id : "+id+" has been deleted !";
    }
}
