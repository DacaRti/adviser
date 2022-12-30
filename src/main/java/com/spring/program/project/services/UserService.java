package com.spring.program.project.services;

import com.spring.program.project.entity.User;
import com.spring.program.project.exceptions.IncorrectEmailException;
import com.spring.program.project.exceptions.UserAlreadyExistException;
import com.spring.program.project.exceptions.UserNotFoundException;
import com.spring.program.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DacaP
 * @version 1.0
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userDao) {
        this.userRepository = userDao;
    }

    public void create(User user) {
        checkUserUniqueness(user);
        checkIncorrectEmail(user.getEmail());
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findById(long id) {
        checkUserIsExist(id);
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }

        return user;
    }

    public void update(User user) {
        checkIncorrectEmail(user.getEmail());
        userRepository.save(user);
    }

    public void remove(long id) {
        checkUserIsExist(id);
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        User user = null;
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = null;
        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    private void checkUserUniqueness(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User by email=" + user.getEmail() + "already exist");
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User by username=" + user.getUsername() + "already exist");
        }
    }

    private void checkUserIsExist(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User by id=" + id + "not found");
        }
    }

    private void checkIncorrectEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new IncorrectEmailException("Email=" + email + " is incorrect");
        }
    }
}
