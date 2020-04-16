package main.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemMessage {
    private String title;
    private String content;
}
