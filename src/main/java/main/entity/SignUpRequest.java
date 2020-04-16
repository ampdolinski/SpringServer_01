package main.entity;

import lombok.*;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String userLogin;
    private String userPassword;
    private String role;
}
