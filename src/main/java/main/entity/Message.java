package main.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@EqualsAndHashCode
@Entity(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    @Column(name = "id_sender")
    private Long idSender;

    @Column(name = "id_receiver")
    private Long idReceiver;

    @Column(name = "message")
    private String message;

    @Column(name = "message_time")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime messageTime;

    @Column(name = "message_delivered")
    private boolean messageDelivered;

}
