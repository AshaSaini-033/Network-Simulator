import java.util.*;

public class EndDevice extends Device{
    String ip;
     String mac;
     HashMap<Integer, String> ports =
        new HashMap<>();
    public EndDevice(String name, String ip) {
        super(name);
        this.ip = ip;
        this.mac =
        "AA:BB:"
        + (int)(Math.random()*99);
    }

public void send(String dest, String message) {

    System.out.println("\n trasmission start .........");
    System.out.println(name + " wants to send data to " + dest);

    // ACCESS CONTROL (CSMA/CD simulation)
    Random rand = new Random();
    int delay = rand.nextInt(3); //Generates a random number (0, 1, or 2).

    System.out.println("access Control..." + name + " sensing medium...");
    
    try {
        Thread.sleep(delay * 500); //Simulates the time taken to sense the medium or wait after a collision.
    } catch (Exception e) {}

    if (delay == 0) { //If the delay is 0, we simulate a Collision. In reality, the PC would wait and retry
        System.out.println("access Control... collision possible  retrying...");
    } else {
        System.out.println("access Control... Medium free, sending...");
    }

    // ERROR CONTROL (Parity)
    Frame frame = new Frame(this.name, dest, message);
    System.out.println("error ctrl... parity bit added : " + frame.parity);

    //  SEND FRAME
    for (Device d : conn) {
        d.receive(frame, this);
    }

    //  FLOW CONTROL (Stop & Wait)
    System.out.println("flow ctrl waiting for ack..");
    try {
        Thread.sleep(500); //Simulates Propagation Delay.
    } catch (Exception e) {}

    System.out.println("flow ctrl ack received at " + name + " done ");

    System.out.println("TRANSMISSION END.....\n");
}
    @Override

public void receive(Frame frame, Device from) {

    if (frame.destination.equals(this.name)) {

        System.out.println(name + " received frame");

        if (frame.checkError()) {
            System.out.println("error ctrl... No error in data");
            System.out.println("DATA: " + frame.data);
        } else {
            System.out.println("error ctrl...  error detected");
        }
    }
}
public void sendWithSlidingWindow(String dest, String message) {

    int windowSize = 3; //number of chunks 

    System.out.println("\n sliding window ....");

    for (int i = 0; i < message.length(); i += windowSize) { //loops through the message in "jumps.

        int end = Math.min(i + windowSize, message.length()); //calculates the end of the current chunk.
        String chunk = message.substring(i, end); //cut piece of msg 

        System.out.println(" sliding window sending : " + chunk);

        Frame frame = new Frame(this.name, dest, chunk); //wrap chunk in frame

        for (Device d : conn) {
            d.receive(frame, this);
        }

        System.out.println("sliding window ack received for: " + chunk); //ack 
    }

    System.out.println("sliding window end \n");
}

   //2nd submmission
public void sendPacket(String destIP,
                       String msg,
                       Router r) {

    Packet p =
            new Packet(ip,
                       destIP,
                       msg);

    System.out.println(
            "\nnetwork layer..."
    );

    System.out.println(
            name
            + " sending packet"
    );

    r.receivePacket(p);
}

// =====================================
    // TRANSPORT LAYER
    // =====================================

    public void assignPort(int port,
                           String process) {

        ports.put(port, process);

        System.out.println(
                process
                + " running on port "
                + port
        );
    }

    public void sendSegment(int srcPort,
                            int destPort,
                            String msg,
                            EndDevice d) {

        Segment s =
                new Segment(
                        srcPort,
                        destPort,
                        msg
                );

        System.out.println(
                "\ntransport layer..."
        );

        d.receiveSegment(s);
    }

    public void receiveSegment(Segment s) {

        System.out.println(
                "\nsegment received"
        );

        String process =
                ports.get(s.destPort);

        if (process != null) {

            System.out.println(
                    "process : "
                    + process
            );

            System.out.println(
                    "data : "
                    + s.data
            );

        } else {

            System.out.println(
                    "port closed"
            );
        }
    }
}


