public class Segment {

    int srcPort;
    int destPort;

    String data;

    public Segment(int s,
                   int d,
                   String data) {

        srcPort = s;
        destPort = d;
        this.data = data;
    }
}