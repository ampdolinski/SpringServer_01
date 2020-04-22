package main;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author Adam Doliński
 * 27.03.2020
 */

@Component
@Getter
public final class ConstantsPackage {

    public static final String INSTANTIATION_NOT_ALLOWED = "Instantiation not allowed";
    public static final String END_MESSAGE_MARKER = ":END";

    public static final int DEFAULT_PORT = 8080;
    public static final InetSocketAddress DEFAULT_SOCKET_PORT = new InetSocketAddress(DEFAULT_PORT);
    public static final int DEFAULT_BUFFER_BYTE_SIZE = 1024;
    public static final int DEFAULT_TIMEOUT_LIMIT = 30; //in seconds
    public static final String NEWLINE = "\n";

    public static final int DEFAULT_NUMBER_OF_THREADS = 1;


    public static final long DOWNTIME_TOLERANCE_IN_MILLISECONDS = 15000;
    public static final long DOWNTIME_TOLERANCE_DEAD_SERVICE_IN_MILLISECONDS = 30000;
    public static final long PULSE_DELAY_IN_MILLISECONDS = 5000;
    public static final int POOL_SIZE = 1;

}