package main.service;

import main.entity.Role;
import main.entity.User;
import main.entity.dto.UserDTO;
import main.entity.mapper.UserMapper;
import main.exception.PasswordsDoNotMatchException;
import main.exception.UserNotFoundException;
import main.exception.WrongOldPasswordException;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@Service
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder encoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User getUserByUserName(String userName) throws UserNotFoundException {
        return userRepository.findUserByUserLogin(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found with userName: " + userName));
    }

    public Optional<User> getOptionalUserByUserName(String userName) {
        return userRepository.findUserByUserLogin(userName);
    }

    public void saveUser(UserDTO userDTO) {
        userDTO.setUserPassword(encoder.encode(userDTO.getUserPassword()));
        User userToSave = UserMapper.map(userDTO);
        userRepository.save(userToSave);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.save(user);
    }

    public void changePassword(String userName, String oldPassword, String newPassword, String confirmPassword)
            throws UserNotFoundException, PasswordsDoNotMatchException, WrongOldPasswordException {
        User userToChangePassword = findUserToChangePassword(userName);

        checkIfPasswordEqualsConfirmPassword(newPassword, confirmPassword);
        if (!encoder.matches(oldPassword, userToChangePassword.getUserPassword())) {
            throw new WrongOldPasswordException();
        }

        changedPasswordAndSave(newPassword, userToChangePassword);
    }

    //if we add later some access through email, we can use this
//    public void restorePassword(String userName, String email, String password, String confirmPassword)
//            throws UserNotFoundException, PasswordsDoNotMatchException, EmailsAreNotEqualException {
//        User userToChangePassword = findUserToChangePassword(userName);
//
//        checkIfPasswordEqualsConfirmPassword(password, confirmPassword);
//        if(!userToChangePassword.getEmail().equals(email)){
//            throw new EmailsAreNotEqualException();
//        }
//
//        changedPasswordAndSave(password, userToChangePassword);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUserLogin(username)
                .map(existingProfile -> new org.springframework.security.core.userdetails.User(
                        existingProfile.getUserLogin(),
                        existingProfile.getUserPassword(),
                        parseRoles(existingProfile.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Collection<? extends GrantedAuthority> parseRoles(Role roles) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles.name()));
    }

    private User findUserToChangePassword(String userName) throws UserNotFoundException {
        return userRepository.findUserByUserLogin(userName)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userName + " not found"));
    }

    private void checkIfPasswordEqualsConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }
    }

    private void changedPasswordAndSave(String newPassword, User userToChangePassword) {
        userToChangePassword.setUserPassword(encoder.encode(newPassword));
        userRepository.save(userToChangePassword);
    }
}
