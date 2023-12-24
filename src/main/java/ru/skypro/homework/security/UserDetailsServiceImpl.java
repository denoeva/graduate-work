package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth_register.Register;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;

/**
 * The class with methods for working with authentication and creating a new user account
 *
 * @author Sulaeva Marina
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * The method to find user in Database by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AdsUserDetails(userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    /**
     * The method for checking user exists
     */
    public boolean userExists(String username) {
        Users userNotExists = new Users();
        Users users = userRepository.findByUsername(username).orElse(userNotExists);
        return !userNotExists.equals(users);
    }

    /**
     * The method for creating user
     */
    public void createUser(Register register, String password) {
        Users users = new Users();
        users.setPassword(password);
        users.setPhone(register.getPhone());
        users.setUsername(register.getUsername());
        users.setFirstName(register.getFirstName());
        users.setLastName(register.getLastName());
        users.setRole(register.getRole());
        userRepository.save(users);
    }
}