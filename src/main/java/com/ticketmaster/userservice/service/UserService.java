package com.ticketmaster.userservice.service;

import com.ticketmaster.userservice.Exceptions.UserAlreadyExistsException;
import com.ticketmaster.userservice.models.User;
import com.ticketmaster.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    public String saveUser(User user) throws UserAlreadyExistsException {
     //save user to database, if email is unique
        log.debug("Checking user -"+user.getId()+ " exists");
        boolean userExists = userRepository.findByEmail(user.getEmail()).isEmpty();
        if(!userExists){
            log.error("User Already Exists!!");
            throw new UserAlreadyExistsException("User with provided email already exists");
        }
        log.debug("Saving user -"+user.getId());
        user = userRepository.save(user);
        return user.getId().toString();
    }

    public void updateUser(User user) {
        //update user details if anything new

        User u = userRepository.getReferenceById(user.getId());
    }

    public com.ticketmaster.userservice.records.User getUser(Long id) throws EntityNotFoundException{
       User u = userRepository.getReferenceById(id);
       com.ticketmaster.userservice.records.User user = new com.ticketmaster.userservice.records.User(u.getUserName(), "", u.getEmail(),u.getFirstName(), u.getLastName());



     return user;
    }

    //this is service


}
