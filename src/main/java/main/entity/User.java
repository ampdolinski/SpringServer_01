package main.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Adam Doliński
 * 08.04.2020
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@EqualsAndHashCode
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;
}

