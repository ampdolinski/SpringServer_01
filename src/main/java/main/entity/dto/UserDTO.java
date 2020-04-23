package main.entity.dto;

import lombok.Data;
import main.entity.Role;

import java.time.LocalDate;

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
    private LocalDate createdAt;
}
