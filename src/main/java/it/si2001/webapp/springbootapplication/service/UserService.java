package it.si2001.webapp.springbootapplication.service;

import it.si2001.webapp.springbootapplication.model.User;
import it.si2001.webapp.springbootapplication.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public List<User> getCustomers()   {
        return userRepository.findCustomer();
    }
    @Transactional
    public Optional<User> getUserByMail(String email){
        return userRepository.findByEMail(email);
    }
    @Transactional
    public Optional<User> getUser(int id) throws ResourceNotFoundException { return userRepository.findById(id); }
    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }

}
