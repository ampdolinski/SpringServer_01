package main.entity.mapper;

import main.entity.User;
import main.entity.dto.UserDTO;

/**
 * @author Adam Doliński
 * 09.04.2020
 */
public class UserMapper {

    public static User map(UserDTO userDTO) {
    User user = new User();

    user.setId(userDTO.getId());
    user.setUserLogin(userDTO.getUserLogin());
    user.setUserPassword(userDTO.getUserPassword());
    user.setRole(userDTO.getRole());
    user.setCreatedAt(userDTO.getCreatedAt());

    return user;
    }
}
