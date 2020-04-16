package main.entity.dto;

import main.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@Data
public class UserDTO {

    private Long id;
    private String userLogin;
    private String userPassword;
    private Role role;
    private LocalDateTime createdAt;
}
