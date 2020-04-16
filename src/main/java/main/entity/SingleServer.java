package main.entity;

import lombok.*;

import javax.persistence.*;

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
@Entity(name = "server")
public class SingleServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server_login")
    private String serverLogin;

}
