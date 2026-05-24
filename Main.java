import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n=========== NETWORK SIMULATOR ===========");

        // DEVICES
        EndDevice pc1 =
                new EndDevice(
                        "PC1",
                        "192.168.1.2"
                );

        EndDevice pc2 =
                new EndDevice(
                        "PC2",
                        "10.0.0.2"
                );

        EndDevice pc3 =
                new EndDevice(
                        "PC3",
                        "192.168.1.3"
                );

        // HUB + SWITCH
        Hub hub =
                new Hub("Hub1");

        Switch sw =
                new Switch("Switch1");

        // ROUTER
        Router r1 =
                new Router("Router1");

        // CONNECTIONS
        pc1.connect(hub);
        pc2.connect(sw);
        pc3.connect(sw);

        hub.connect(pc1);

        sw.connect(pc2);
        sw.connect(pc3);

        // ROUTER CONNECTION
        r1.connect(sw);
        sw.connect(r1);

        // =========================================
        // DATA LINK LAYER
        // =========================================

        System.out.println(
                "\n========== DATA LINK LAYER =========="
        );

        pc1.send(
                "PC2",
                "hello from pc1"
        );

        // =========================================
        // NETWORK LAYER
        // =========================================

        System.out.println(
                "\n========== NETWORK LAYER =========="
        );

        // ARP TABLE
        r1.addARP(
                pc1.ip,
                pc1.mac
        );

        r1.addARP(
                pc2.ip,
                pc2.mac
        );

        r1.addARP(
                pc3.ip,
                pc3.mac
        );

        // ROUTES
        r1.addRoute(
                "192.168.1.0",
                "24",
                "direct"
        );

        r1.addRoute(
                "10.0.0.0",
                "24",
                "direct"
        );

        // SEND PACKET
        pc1.sendPacket(
                pc2.ip,
                "network layer packet",
                r1
        );

        // RIP
        r1.sendRIP();

        // =========================================
        // TRANSPORT LAYER
        // =========================================

        System.out.println(
                "\n========== TRANSPORT LAYER =========="
        );

        // PORTS
        pc1.assignPort(
                5000,
                "chat"
        );

        pc2.assignPort(
                6000,
                "chat"
        );

        pc1.assignPort(
                5050,
                "file"
        );

        pc2.assignPort(
                6060,
                "file"
        );

        // SLIDING WINDOW
        pc1.sendWithSlidingWindow(
                "PC2",
                "HELLOWORLD"
        );

        // =========================================
        // APPLICATION LAYER
        // =========================================

        System.out.println(
                "\n========== APPLICATION LAYER =========="
        );

        // CHAT APPLICATION
        ChatApp chat =
                new ChatApp();

        chat.sendMessage(
                pc1,
                pc2,
                "hello from chat app"
        );

        // FILE TRANSFER
        FileService file =
                new FileService();

        file.transferFile(
                pc1,
                pc2,
                "cn_notes.pdf"
        );

        // =========================================

        System.out.println(
                "\n=========== SIMULATION COMPLETE =========="
        );
    }
}