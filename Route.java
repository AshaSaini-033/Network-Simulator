public class Route {

    String network;
    String mask;
    String nextHop;

    public Route(String n,
                 String m,
                 String hop) {

        network = n;
        mask = m;
        nextHop = hop;
    }
}