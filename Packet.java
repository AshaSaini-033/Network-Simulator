public class Packet {

    String srcIP;
    String destIP;
    String data;

    public Packet(String s,
                  String d,
                  String data) {

        this.srcIP = s;
        this.destIP = d;
        this.data = data;
    }
}