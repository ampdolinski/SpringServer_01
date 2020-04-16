package main.service;

import main.entity.Role;
import main.entity.User;
import main.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class InitialDataLoader {
    private UserRepository clientRepository;

    @Autowired
    private UserService userServiceSecurity;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @EventListener
    @Transactional
    public void init(ApplicationReadyEvent applicationReadyEvent) {

        User userAsServer = User.builder()
                .userLogin("server")
                .userPassword(encoder.encode("server"))
                .role(Role.SERVER)
                .createdAt(LocalDateTime.now())
                .build();

        User user1 = User.builder()
                .userLogin("client1")
                .userPassword(encoder.encode("client1"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .userLogin("client2")
                .userPassword(encoder.encode("client2"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        User user3 = User.builder()
                .userLogin("client3")
                .userPassword(encoder.encode("client3"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        User user4 = User.builder()
                .userLogin("client4")
                .userPassword(encoder.encode("client4"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();


        List<User> clients = new ArrayList<>
                (Arrays.asList(userAsServer, user1, user2, user3, user4));

        clients.forEach(clientRepository::save);
    }
}