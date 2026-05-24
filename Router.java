import java.util.*;

public class Router extends Device {

    ArrayList<Route> routes = new ArrayList<>();
    ArrayList<ARPEntry> arpTable = new ArrayList<>();

    public Router(String name) {
        super(name);
    }
    @Override
public void receive(Frame frame,
                    Device from) {

    System.out.println(
            "\nrouter received frame"
    );
}

    // add route
    public void addRoute(String network,
                         String mask,
                         String nextHop) {

        routes.add(
                new Route(network, mask, nextHop)
        );

        System.out.println(name
                + " route added : "
                + network + "/" + mask);
    }

    // arp mapping
    public void addARP(String ip,
                       String mac) {

        arpTable.add(
                new ARPEntry(ip, mac)
        );

        System.out.println(name
                + " stored arp : "
                + ip + " -> " + mac);
    }

    // find mac
    public String getMAC(String ip) {

        for (ARPEntry a : arpTable) {

            if (a.ip.equals(ip)) {
                return a.mac;
            }
        }

        return "not found";
    }

    // receive packet
    public void receivePacket(Packet p) {

        System.out.println("\n"
                + name
                + " received packet");

        System.out.println(
                "src : " + p.srcIP
        );

        System.out.println(
                "dest : " + p.destIP
        );

        routePacket(p);
    }

    // routing
    public void routePacket(Packet p) {

        Route best = null;

        int longest = -1;

        for (Route r : routes) {

            int len =
                    Integer.parseInt(r.mask);

            if (p.destIP.startsWith(
                    r.network.substring(0, 3))) {

                if (len > longest) {

                    longest = len;
                    best = r;
                }
            }
        }

        if (best != null) {

            System.out.println(
                    "forwarding using "
                    + best.network
            );

        } else {

            System.out.println(
                    "no route found"
            );
        }
    }

    // rip
    public void sendRIP() {

        System.out.println(
                "\n" + name
                + " sending RIP updates"
        );

        for (Route r : routes) {

            System.out.println(
                    "route : "
                    + r.network
            );
        }
    }
}