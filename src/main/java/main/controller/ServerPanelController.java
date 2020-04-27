package main.controller;

import main.common.SystemMessage;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ServerPanelController {

    @GetMapping("/server")
    public String showServerPanelView() {
        return "server";
    }

    @PostMapping("/server")
    public String showServer(Model model) {

        String serverName = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("message",
                new SystemMessage("Success", "You just logged-in as SERVER"));
        return "message";
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final String AVAILABLE_SERVER_SOCKET = "availableServerSocket";

    public void startingServer() {
        final GenericXmlApplicationContext context = ServerPanelController.setupContext();
        final SimpleGatewayController gateway = context.getBean(SimpleGatewayController.class);
        final AbstractServerConnectionFactory crLfServer = context.getBean(AbstractServerConnectionFactory.class);

    }

    private static GenericXmlApplicationContext setupContext() {

        final GenericXmlApplicationContext context = new GenericXmlApplicationContext();

        if (System.getProperty(AVAILABLE_SERVER_SOCKET) == null) {
            System.out.print("Detect open server socket...");
            int availableServerSocket = SocketUtils.findAvailableTcpPort(5678);

            final Map<String, Object> sockets = new HashMap<>();
            sockets.put(AVAILABLE_SERVER_SOCKET, availableServerSocket);

            final MapPropertySource propertySource = new MapPropertySource("sockets", sockets);

            context.getEnvironment().getPropertySources().addLast(propertySource);
        }

        System.out.println("using port " + context.getEnvironment().getProperty(AVAILABLE_SERVER_SOCKET));

        context.load("classpath:application.properties");
        context.registerShutdownHook();
        context.refresh();

        return context;

    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
