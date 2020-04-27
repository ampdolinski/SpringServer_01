package main.controller;

import main.ConstantsPackage;
import main.common.SystemMessage;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    private TcpSendingMessageHandler tcpSendingMessageHandler = new TcpSendingMessageHandler();
    private TcpOutboundGateway tcpOutboundGateway = new TcpOutboundGateway();
    private TcpReceivingChannelAdapter tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
    public TcpNioServerConnectionFactory tcpNioServerConnectionFactory;

    //create an asynchronous server socket channel
    public void firstFunctionFromSpringIntegrationTCPIP() {

        tcpNioServerConnectionFactory.setPort(ConstantsPackage.DEFAULT_PORT);
        tcpNioServerConnectionFactory.run();

        whatToDoWhenServerSocketIsOpenOrNot();

    }

    private void whatToDoWhenServerSocketIsOpenOrNot() {
        assert tcpNioServerConnectionFactory.isRunning();

        System.out.print("TcpNioServerConnectionFactory Channel is open.\n");

        //optional - options of server socket channel
        tcpNioServerConnectionFactory.setSoReceiveBufferSize(ConstantsPackage.DEFAULT_BUFFER_BYTE_SIZE);
        tcpNioServerConnectionFactory.setSoSendBufferSize(ConstantsPackage.DEFAULT_BUFFER_BYTE_SIZE);
        tcpNioServerConnectionFactory.setSoTimeout(ConstantsPackage.DEFAULT_TIMEOUT_LIMIT);

        startWaitingForIncomingClients();

    }

    private void startWaitingForIncomingClients() {
        acceptRequestedClient();
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
