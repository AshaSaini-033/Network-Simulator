import java.util.Random;

public class EndDevice extends Device {
    String ip;

    public EndDevice(String name, String ip) {
        super(name);
        this.ip = ip;
    }

public void send(String dest, String message) {

    System.out.println("\n=== TRANSMISSION START ===");
    System.out.println(name + " wants to send data to " + dest);

    // 🔹 ACCESS CONTROL (CSMA/CD simulation)
    Random rand = new Random();
    int delay = rand.nextInt(3);

    System.out.println("[Access Control] " + name + " sensing medium...");
    
    try {
        Thread.sleep(delay * 500);
    } catch (Exception e) {}

    if (delay == 0) {
        System.out.println("[Access Control] ⚠ Collision possible! Retrying...");
    } else {
        System.out.println("[Access Control] Medium free, sending...");
    }

    // 🔹 ERROR CONTROL (Parity)
    Frame frame = new Frame(this.name, dest, message);
    System.out.println("[Error Control] Parity bit added: " + frame.parity);

    // 🔹 SEND FRAME
    for (Device d : connections) {
        d.receive(frame, this);
    }

    // 🔹 FLOW CONTROL (Stop & Wait)
    System.out.println("[Flow Control] Waiting for ACK...");
    try {
        Thread.sleep(500);
    } catch (Exception e) {}

    System.out.println("[Flow Control] ACK received at " + name + " ✔");

    System.out.println("=== TRANSMISSION END ===\n");
}
    @Override

public void receive(Frame frame, Device from) {

    if (frame.destination.equals(this.name)) {

        System.out.println(name + " received frame");

        if (frame.checkError()) {
            System.out.println("[Error Control] ✔ No error in data");
            System.out.println("DATA: " + frame.data);
        } else {
            System.out.println("[Error Control] ❌ Error detected!");
        }
    }
}
public void sendWithSlidingWindow(String dest, String message) {

    int windowSize = 3;

    System.out.println("\n=== SLIDING WINDOW START ===");

    for (int i = 0; i < message.length(); i += windowSize) {

        int end = Math.min(i + windowSize, message.length());
        String chunk = message.substring(i, end);

        System.out.println("[Sliding Window] Sending: " + chunk);

        Frame frame = new Frame(this.name, dest, chunk);

        for (Device d : connections) {
            d.receive(frame, this);
        }

        System.out.println("[Sliding Window] ACK received for: " + chunk);
    }

    System.out.println("=== SLIDING WINDOW END ===\n");
}

   
}